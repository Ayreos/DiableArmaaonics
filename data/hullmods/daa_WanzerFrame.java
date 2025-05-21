package data.hullmods;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
//import com.fs.starfarer.api.impl.campaign.ids.HullMods;

public class daa_WanzerFrame extends BaseHullMod {
    static {
        System.out.println("[DEBUG] WanzerFrame class loaded");
    }
    private final float EMP_RESIST = 33, DISABLE_RESIST = 66;

    @Override
    public String getDescriptionParam(int index, HullSize hullSize) {
        if (index == 0) {
            return "" + (int) EMP_RESIST + "%";
        }
        if (index == 1) {
            return "" + (int) DISABLE_RESIST + "%";
        }
        return null;
    }

    @Override
    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		// empty
	}

    @Override
    public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
		// Applies the same stat bonuses as the original hullmod from DA
        ship.getMutableStats().getEmpDamageTakenMult().modifyMult(id, (100 - EMP_RESIST) / 100);
        ship.getMutableStats().getEngineDamageTakenMult().modifyMult(id, (100 - DISABLE_RESIST) / 100);
        ship.getMutableStats().getWeaponDamageTakenMult().modifyMult(id, (100 - DISABLE_RESIST) / 100);
		
		// Removed listener for AI movement
    }

    @Override
    public boolean isApplicableToShip(ShipAPI ship) {
        // Hullmod gets restricted to armaaonics wanzers
        return (ship.getHullSpec().getHullId().startsWith("daa_"));
    }
}
