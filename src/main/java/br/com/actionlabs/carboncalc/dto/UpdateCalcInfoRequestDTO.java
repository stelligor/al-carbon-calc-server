package br.com.actionlabs.carboncalc.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCalcInfoRequestDTO {
  private String id;
  private Double energyConsumption;
  private String uf;
  private List<TransportationDTO> transportation;
  private int solidWasteTotal;
  private double recyclePercentage;
}
