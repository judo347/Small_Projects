package dk.model;

public class PlayerInfo {

    private int playerLevel;
    private int praporLoyaltyLevel = 0;
    private int therapistLoyaltyLevel = 0;
    private int skierLoyaltyLevel = 0;
    private int peacekeeperLoyaltyLevel = 0;
    private int mechanicLoyaltyLevel = 0;
    private int ragmanLoyaltyLevel = 0;
    private int jaegerLoyaltyLevel = 0;
    private int fenceLoyaltyLevel = 0;

    public PlayerInfo() {
    }

    public PlayerInfo(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getLoyaltyLevelFromTrader(TraderType traderType){
        switch (traderType){
            case MECHANIC: return mechanicLoyaltyLevel;
            case SKIER: return skierLoyaltyLevel;
            case FENCE: return fenceLoyaltyLevel;
            case JAEGER: return jaegerLoyaltyLevel;
            case PRAPOR: return praporLoyaltyLevel;
            case RAGMAN: return ragmanLoyaltyLevel;
            case THERAPIST: return therapistLoyaltyLevel;
            case PEACEKEEPER: return peacekeeperLoyaltyLevel;
            default: throw new IllegalArgumentException();
        }
    }

    public void incrementLoyaltyLevel(TraderType traderType){
        switch (traderType) {
            case MECHANIC:
                mechanicLoyaltyLevel++;
                break;
            case SKIER:
                skierLoyaltyLevel++;
                break;
            case FENCE:
                fenceLoyaltyLevel++;
                break;
            case JAEGER:
                jaegerLoyaltyLevel++;
                break;
            case PRAPOR:
                praporLoyaltyLevel++;
                break;
            case RAGMAN:
                ragmanLoyaltyLevel++;
                break;
            case THERAPIST:
                therapistLoyaltyLevel++;
                break;
            case PEACEKEEPER:
                peacekeeperLoyaltyLevel++;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void incrementPlayerLevel(){
        playerLevel++;
    }
}
