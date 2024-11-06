package br.com.actionlabs.carboncalc.rest;

import br.com.actionlabs.carboncalc.dto.*;

import br.com.actionlabs.carboncalc.model.EnergyEmissionFactor;
import br.com.actionlabs.carboncalc.model.TransportationEmissionFactor;

import br.com.actionlabs.carboncalc.repository.CarbonCalculatorRepository;
import br.com.actionlabs.carboncalc.repository.EnergyEmissionFactorRepository;
import br.com.actionlabs.carboncalc.repository.SolidWasteEmissionFactorRepository;
import br.com.actionlabs.carboncalc.repository.TransportationEmissionFactorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import javax.swing.plaf.TreeUI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@Slf4j
public class OpenRestController {

  private final CarbonCalculatorRepository carbonCalculatorRepository;
  private final EnergyEmissionFactorRepository energyEmissionFactorRepository;
  private final TransportationEmissionFactorRepository transportationEmissionFactorRepository;
  private final SolidWasteEmissionFactorRepository solidWasteEmissionFactorRepository;

  @PostMapping("start-calc")
  public ResponseEntity<StartCalcResponseDTO> startCalculation(
      @RequestBody StartCalcRequestDTO request) {
    // Validação de dados obrigatórios
    if (request.getName() == null || request.getEmail() == null || request.getPhoneNumber() == null
        || request.getUf() == null) {
      return ResponseEntity.badRequest().body(new StartCalcResponseDTO("Todos os parâmetros são obrigatórios."));
    }

    // Armazenar as informações do usuário no banco de dados
    StartCalcRequestDTO newCalc = new StartCalcRequestDTO(request.getName(), request.getEmail(),
        request.getPhoneNumber(), request.getUf());
    carbonCalculatorRepository.save(newCalc);

    // Criar um novo cálculo com o ID gerado
    StartCalcResponseDTO calculation = new StartCalcResponseDTO(newCalc);
    carbonCalculatorRepository.save(calculation);

    // Retornar o ID do cálculo
    return ResponseEntity.ok(calculation);
  }

  @PutMapping("info")
  public ResponseEntity<UpdateCalcInfoResponseDTO> updateInfo(
      @RequestBody UpdateCalcInfoRequestDTO request) {

    Optional<CarbonCalculation> calculationOpt = carbonCalculationRepository.findById(request.getId());
    if (calculationOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    UpdateCalcInfoRequestDTO calculation = calculationOpt.get();

    // Atualizar informações de consumo de energia, transporte e resíduos sólidos
    calculation.setEnergyConsumption(request.getEnergyConsumption());
    calculation.setEnergyUf(request.getUf());

    List<TransportationDTO> listTransportRequest = new List<TransportationDTO>();

    for (int i = 0; i <= request.getTransportation().size(); i++) {
      listTransportRequest.add(request.getTransportation().get(i).getMonthlyDistance(),
          request.getTransportation().get(i).getType());
    }
    calculation.setTransportation(listTransportRequest);

    calculation.setSolidWasteProduction(request.getSolidWasteProduction());
    calculation.setRecyclePercentage(request.getRecyclePercentage());

    // Salvar as alterações no banco de dados
    carbonCalculationRepository.save(calculation);

    // Retornar resposta
    UpdateCalcInfoResponseDTO response = new UpdateCalcInfoResponseDTO(true);
    return ResponseEntity.ok(response);
  }

  @GetMapping("result/{id}")
  public ResponseEntity<CarbonCalculationResultDTO> getResult(@PathVariable String id) {
    Optional<CarbonCalculation> calculationOpt = carbonCalculationRepository.findById(id);
    if (calculationOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    CarbonCalculation calculation = calculationOpt.get();

    // Obter os fatores de emissão
    EnergyEmissionFactor energyFactor = energyEmissionFactorRepository.findByUf(calculation.getEnergyUf());
    TransportationEmissionFactor transportFactor = transportationEmissionFactorRepository
        .findByType(calculation.getTransportType());
    SolidWasteEmissionFactor wasteFactor = solidWasteEmissionFactorRepository
        .findByRecyclePercentage(calculation.getRecyclePercentage());

    // Calcular a emissão total de carbono
    double energyEmission = calculation.getEnergyConsumption() * energyFactor.getFactor();
    double transportEmission = calculation.getTransportDistance() * transportFactor.getFactor();
    double wasteEmission = calculation.getSolidWasteProduction() * wasteFactor.getFactor();

    double totalEmission = energyEmission + transportEmission + wasteEmission;

    // Criar DTO de resultado
    CarbonCalculationResultDTO resultDTO = new CarbonCalculationResultDTO(totalEmission);
    return ResponseEntity.ok(resultDTO);
  }
}
