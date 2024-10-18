package br.com.actionlabs.carboncalc.rest;

import br.com.actionlabs.carboncalc.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open")
@RequiredArgsConstructor
@Slf4j
public class OpenRestController {

  @PostMapping("start-calc")
  public StartCalcResponseDTO startCalculation(@RequestBody StartCalcRequestDTO request) {
    throw new RuntimeException("Not implemented");
  }

  @PutMapping("info")
  public UpdateCalcInfoResponseDTO updateInfo(@RequestBody UpdateCalcInfoRequestDTO request) {
    throw new RuntimeException("Not implemented");
  }

  @GetMapping("result/{id}")
  public CarbonCalculationResultDTO getResult(@PathVariable String id) {
    throw new RuntimeException("Not implemented");
  }
}
