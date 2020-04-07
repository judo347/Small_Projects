package dk.model;

public class PlayerInfo {

    private int playerLevel = 1;
    private int praporLoyaltyLevel = 1;
    private int therapistLoyaltyLevel = 1;
    private int skierLoyaltyLevel = 1;
    private int peacekeeperLoyaltyLevel = 1;
    private int mechanicLoyaltyLevel = 1;
    private int ragmanLoyaltyLevel = 1;
    private int jaegerLoyaltyLevel = 1;
    private int fenceLoyaltyLevel = 1;

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

    public void setLoyaltyLevel(TraderType traderType, int desiredLevel){
        switch (traderType) {
            case MECHANIC:
                mechanicLoyaltyLevel = desiredLevel;
                break;
            case SKIER:
                skierLoyaltyLevel = desiredLevel;
                break;
            case FENCE:
                fenceLoyaltyLevel = desiredLevel;
                break;
            case JAEGER:
                jaegerLoyaltyLevel = desiredLevel;
                break;
            case PRAPOR:
                praporLoyaltyLevel = desiredLevel;
                break;
            case RAGMAN:
                ragmanLoyaltyLevel = desiredLevel;
                break;
            case THERAPIST:
                therapistLoyaltyLevel = desiredLevel;
                break;
            case PEACEKEEPER:
                peacekeeperLoyaltyLevel = desiredLevel;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void incrementPlayerLevel(){
        playerLevel++;
    }

    public void reload(PlayerInfo playerInfo){
        this.playerLevel = playerInfo.playerLevel;
        this.peacekeeperLoyaltyLevel = playerInfo.peacekeeperLoyaltyLevel;
        this.therapistLoyaltyLevel = playerInfo.therapistLoyaltyLevel;
        this.ragmanLoyaltyLevel = playerInfo.ragmanLoyaltyLevel;
        this.fenceLoyaltyLevel = playerInfo.fenceLoyaltyLevel;
        this.praporLoyaltyLevel = playerInfo.praporLoyaltyLevel;
        this.jaegerLoyaltyLevel = playerInfo.jaegerLoyaltyLevel;
        this.skierLoyaltyLevel = playerInfo.skierLoyaltyLevel;
        this.mechanicLoyaltyLevel = playerInfo.mechanicLoyaltyLevel;
    }
}
