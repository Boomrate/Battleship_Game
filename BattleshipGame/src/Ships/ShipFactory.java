package Ships;

public class ShipFactory {
    
    public Ship getShip(String shipType){
      if(shipType == null){
         return null;
      }		
      if(shipType.equalsIgnoreCase("Aircraft")){
         return new Aircraft();
      }
      else if(shipType.equalsIgnoreCase("Battle")){
         return new Battle();
      }
      else if(shipType.equalsIgnoreCase("Submarine")){
         return new Submarine();
      }
      else if(shipType.equalsIgnoreCase("Destroyer")){
         return new Destroyer();
      }
      else if(shipType.equalsIgnoreCase("Patrol")){
         return new Patrol();
      }
      
      return null;
   }
}
