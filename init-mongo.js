// init-mongo.js
db = db.getSiblingDB('carbon-calc');

db.getCollection("energyEmissionFactor").insertMany([
  { "uf": "AC", "factor": 0.45 },
  { "uf": "AL", "factor": 0.5 },
  { "uf": "AM", "factor": 0.75 },
  { "uf": "AP", "factor": 0.7 },
  { "uf": "BA", "factor": 0.55 },
  { "uf": "CE", "factor": 0.52 },
  { "uf": "DF", "factor": 0.6 },
  { "uf": "ES", "factor": 0.5 },
  { "uf": "GO", "factor": 0.48 },
  { "uf": "MA", "factor": 0.65 },
  { "uf": "MT", "factor": 0.43 },
  { "uf": "MS", "factor": 0.44 },
  { "uf": "MG", "factor": 0.53 },
  { "uf": "PA", "factor": 0.58 },
  { "uf": "PB", "factor": 0.51 },
  { "uf": "PE", "factor": 0.54 },
  { "uf": "PI", "factor": 0.56 },
  { "uf": "PR", "factor": 0.4 },
  { "uf": "RJ", "factor": 0.57 },
  { "uf": "RN", "factor": 0.5 },
  { "uf": "RO", "factor": 0.68 },
  { "uf": "RR", "factor": 0.8 },
  { "uf": "RS", "factor": 0.42 },
  { "uf": "SC", "factor": 0.4 },
  { "uf": "SE", "factor": 0.54 },
  { "uf": "SP", "factor": 0.47 },
  { "uf": "TO", "factor": 0.65 }]
);


db.getCollection("transportationEmissionFactor").insertMany([
  { "type": "CAR", "factor": 0.19 },
  { "type": "MOTORCYCLE", "factor": 0.09 },
  { "type": "PUBLIC_TRANSPORT", "factor": 0.04 }
]);


db.getCollection("solidWasteEmissionFactor").insertMany([
    { "uf": "AC", "recyclableFactor": 0.45, "nonRecyclableFactor": 0.99 },
    { "uf": "AL", "recyclableFactor": 0.43, "nonRecyclableFactor": 0.95 },
    { "uf": "AM", "recyclableFactor": 0.5, "nonRecyclableFactor": 1.05 },
    { "uf": "AP", "recyclableFactor": 0.48, "nonRecyclableFactor": 1.02 },
    { "uf": "BA", "recyclableFactor": 0.42, "nonRecyclableFactor": 0.96 },
    { "uf": "CE", "recyclableFactor": 0.44, "nonRecyclableFactor": 0.98 },
    { "uf": "DF", "recyclableFactor": 0.41, "nonRecyclableFactor": 0.93 },
    { "uf": "ES", "recyclableFactor": 0.46, "nonRecyclableFactor": 0.97 },
    { "uf": "GO", "recyclableFactor": 0.43, "nonRecyclableFactor": 0.94 },
    { "uf": "MA", "recyclableFactor": 0.47, "nonRecyclableFactor": 1.01 },
    { "uf": "MT", "recyclableFactor": 0.42, "nonRecyclableFactor": 0.93 },
    { "uf": "MS", "recyclableFactor": 0.41, "nonRecyclableFactor": 0.91 },
    { "uf": "MG", "recyclableFactor": 0.45, "nonRecyclableFactor": 0.95 },
    { "uf": "PA", "recyclableFactor": 0.48, "nonRecyclableFactor": 1.02 },
    { "uf": "PB", "recyclableFactor": 0.43, "nonRecyclableFactor": 0.96 },
    { "uf": "PE", "recyclableFactor": 0.44, "nonRecyclableFactor": 0.98 },
    { "uf": "PI", "recyclableFactor": 0.45, "nonRecyclableFactor": 0.97 },
    { "uf": "PR", "recyclableFactor": 0.4, "nonRecyclableFactor": 0.9 },
    { "uf": "RJ", "recyclableFactor": 0.46, "nonRecyclableFactor": 0.98 },
    { "uf": "RN", "recyclableFactor": 0.43, "nonRecyclableFactor": 0.96 },
    { "uf": "RO", "recyclableFactor": 0.49, "nonRecyclableFactor": 1.03 },
    { "uf": "RR", "recyclableFactor": 0.5, "nonRecyclableFactor": 1.05 },
    { "uf": "RS", "recyclableFactor": 0.4, "nonRecyclableFactor": 0.91 },
    { "uf": "SC", "recyclableFactor": 0.41, "nonRecyclableFactor": 0.92 },
    { "uf": "SE", "recyclableFactor": 0.44, "nonRecyclableFactor": 0.98 },
    { "uf": "SP", "recyclableFactor": 0.42, "nonRecyclableFactor": 0.94 },
    { "uf": "TO", "recyclableFactor": 0.48, "nonRecyclableFactor": 1.02 }
]);