package com.hbm.lib;

import com.hbm.handler.GunConfiguration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public final class HBMSoundHandler {

    public static List<SoundEvent> ALL_SOUNDS = new ArrayList<SoundEvent>();

    public static SoundEvent assemblerOperate;
    public static SoundEvent fel;
    public static SoundEvent siloopen;
    public static SoundEvent siloclose;
    public static SoundEvent pressOperate;
    public static SoundEvent laserBang;
    public static SoundEvent blockDebris;
    public static SoundEvent syringeUse;
    public static SoundEvent sparkShoot;
    public static SoundEvent b92Reload;
    public static SoundEvent techBleep;
    public static SoundEvent techBoop;
    public static SoundEvent reactorStart;
    public static SoundEvent reactorStop;
    public static SoundEvent chemplantOperate;
    public static SoundEvent potatOSRandom;
    public static SoundEvent weaponSpinDown;
    public static SoundEvent weaponSpinUp;
    public static SoundEvent sawShoot;
    public static SoundEvent rpgShoot;
    public static SoundEvent rifleShoot;
    public static SoundEvent defabShoot;
    public static SoundEvent flamethrowerIgnite;
    public static SoundEvent flamethrowerShoot;
    public static SoundEvent tauShoot;
    public static SoundEvent oldExplosion;
    public static SoundEvent ciwsSpindown;
    public static SoundEvent ciwsSpinup;
    public static SoundEvent ciwsFiringLoop;
    public static SoundEvent reloadTurret;
    public static SoundEvent planeShotDown;
    public static SoundEvent bombWhistle;
    public static SoundEvent planeCrash;
    public static SoundEvent missileTakeoff;
    public static SoundEvent bomberSmallLoop;
    public static SoundEvent bomberLoop;
    public static SoundEvent stingerLockon;
    public static SoundEvent trainHorn;
    public static SoundEvent bombDet;
    public static SoundEvent rocketTakeoff;
    public static SoundEvent silencerShoot;
    public static SoundEvent rpgReload;
    public static SoundEvent reloadGrenade;
    public static SoundEvent reloadShotgun;
    public static SoundEvent reloadMag;
    public static SoundEvent reloadRifle;
    public static SoundEvent reloadRevolver;
    public static SoundEvent boatWeapon;
    public static SoundEvent ricochet;
    public static SoundEvent grenadeBounce;
    public static SoundEvent alarmGambit;
    public static SoundEvent revolverShoot;
    public static SoundEvent heavyShoot;
    public static SoundEvent schrabidiumShoot;
    public static SoundEvent revolverShootAlt;
    public static SoundEvent hkShoot;
    public static SoundEvent shotgunShoot;
    public static SoundEvent shottyShoot;
    public static SoundEvent uziShoot;
    public static SoundEvent calShoot;
    public static SoundEvent lacunaeShoot;
    public static SoundEvent fatmanShoot;
    public static SoundEvent osiprShoot;
    public static SoundEvent zomgShoot;
    public static SoundEvent jetpackTank;
    public static SoundEvent nullTau;
    public static SoundEvent immolatorIgnite;
    public static SoundEvent immolatorShoot;
    public static SoundEvent defabSpinup;
    public static SoundEvent cryolatorShoot;
    public static SoundEvent singFlyby;
    public static SoundEvent osiprCharging;
    public static SoundEvent leverActionReload;
    public static SoundEvent follyOpen;
    public static SoundEvent follyReload;
    public static SoundEvent follyClose;
    public static SoundEvent follyFire;
    public static SoundEvent follyBuzzer;
    public static SoundEvent follyAquired;
    public static SoundEvent chopperDrop;
    public static SoundEvent crateBreak;
    public static SoundEvent itemUnpack;
    public static SoundEvent centrifugeOperate;
    public static SoundEvent buttonNo;
    public static SoundEvent buttonYes;
    public static SoundEvent railgunFire;
    public static SoundEvent railgunOrientation;
    public static SoundEvent railgunCharge;
    public static SoundEvent shutdown;
    public static SoundEvent broadcast1;
    public static SoundEvent broadcast2;
    public static SoundEvent broadcast3;
    public static SoundEvent geiger1;
    public static SoundEvent geiger2;
    public static SoundEvent geiger3;
    public static SoundEvent geiger4;
    public static SoundEvent geiger5;
    public static SoundEvent geiger6;
    public static SoundEvent lockOpen;
    public static SoundEvent pinBreak;
    public static SoundEvent pinUnlock;
    public static SoundEvent lockHang;
    public static SoundEvent vaultScrapeNew;
    public static SoundEvent vaultThudNew;
    public static SoundEvent minerOperate;
    public static SoundEvent missileAssembly2;
    public static SoundEvent sonarPing;
    public static SoundEvent radawayUse;
    public static SoundEvent gasmaskScrew;
    public static SoundEvent spray;
    public static SoundEvent repair;
    public static SoundEvent nullChopper;
    public static SoundEvent chopperCharge;
    public static SoundEvent nullCrashing;
    public static SoundEvent chopperDamage;
    public static SoundEvent nullMine;
    public static SoundEvent openDoor;
    public static SoundEvent closeDoor;
    public static SoundEvent bang;
    public static SoundEvent slice;
    public static SoundEvent kaping;
    public static SoundEvent pipePlaced;
    public static SoundEvent tesla;
    public static SoundEvent cybercrab;
    public static SoundEvent osiprReload;
    public static SoundEvent fatmanReload;
    public static SoundEvent soyuzReady;
    public static SoundEvent soyuzTakeOff;
    public static SoundEvent chime;
    public static SoundEvent deagleShoot;
    public static SoundEvent tauChargeLoop;
    public static SoundEvent tauChargeLoop2;
    public static SoundEvent chopperFlyingLoop;
    public static SoundEvent chopperCrashingLoop;
    public static SoundEvent chopperMineLoop;
    public static SoundEvent lacunaeSpinup;
    public static SoundEvent lacunaeSpindown;
    public static SoundEvent teslaShoot;
    public static SoundEvent flamerReload;
    public static SoundEvent stop;
    public static SoundEvent bonk;
    public static SoundEvent glauncher;
    public static SoundEvent hksShoot;
    public static SoundEvent vice;
    public static SoundEvent screm;
    public static SoundEvent upgradePlug;
    public static SoundEvent quadroReload;
    public static SoundEvent fstbmbStart;
    public static SoundEvent fstbmbPing;
    public static SoundEvent sauerGun;
    public static SoundEvent ducc;
    public static SoundEvent whack;
    public static SoundEvent turbofanOperate;
    public static SoundEvent slicer;
    public static SoundEvent megaquacc;
    public static SoundEvent chainsaw;
    public static SoundEvent battery;
    public static SoundEvent rocketFlame;
    public static SoundEvent ballsLaser;
    public static SoundEvent dartShoot;
    public static SoundEvent gluonStart;
    public static SoundEvent gluonLoop;
    public static SoundEvent gluonEnd;
    public static SoundEvent gluonHit;
    public static SoundEvent jetpack;
    public static SoundEvent mukeExplosion;
    public static SoundEvent crucibleStart;
    public static SoundEvent crucibleEnd;
    public static SoundEvent crucibleSwing;
    public static SoundEvent crucibleLoop;
    public static SoundEvent jsg_reload0;
    public static SoundEvent jsg_reload1;
    public static SoundEvent mob_gib;
    public static SoundEvent blood_splat;
    public static SoundEvent hit_dirt;
    public static SoundEvent hit_metal;
    public static SoundEvent hit_flesh;
    public static SoundEvent vomit;
    public static SoundEvent chekhov_fire;
    public static SoundEvent jeremy_fire;
    public static SoundEvent jeremy_reload;
    public static SoundEvent richard_fire;
    public static SoundEvent howard_fire;
    public static SoundEvent howard_reload;
    public static SoundEvent rbmk_explosion;
    public static SoundEvent rbmk_az5_cover;
    public static SoundEvent chungus_lever;
    public static SoundEvent dflash;
    public static SoundEvent cough;
    public static SoundEvent ufoBeam;
    public static SoundEvent ufoBlast;

    public static SoundEvent transitionSealOpen;
    public static SoundEvent garage;
    public static SoundEvent garage_stop;
    public static SoundEvent door_spinny;
    public static SoundEvent wgh_big_start;
    public static SoundEvent wgh_big_stop;
    public static SoundEvent wgh_start;
    public static SoundEvent wgh_stop;
    public static SoundEvent alarm6;
    public static SoundEvent qe_sliding_shut;
    public static SoundEvent qe_sliding_opened;
    public static SoundEvent qe_sliding_opening;
    public static SoundEvent hatch_open;
    public static SoundEvent sliding_seal_open;
    public static SoundEvent sliding_seal_stop;

    public static SoundEvent alarmHatch = registerBypass("alarm.hatch");
    public static SoundEvent alarmAutopilot = registerBypass("alarm.autopilot");
    public static SoundEvent alarmAMSSiren = registerBypass("alarm.amsSiren");
    public static SoundEvent alarmBlastDoor = registerBypass("alarm.blastDoorAlarm");
    public static SoundEvent alarmAPCLoop = registerBypass("alarm.apcLoop");
    public static SoundEvent alarmKlaxon = registerBypass("alarm.klaxon");
    public static SoundEvent alarmFoKlaxonA = registerBypass("alarm.foKlaxonA");
    public static SoundEvent alarmFoKlaxonB = registerBypass("alarm.foKlaxonB");
    public static SoundEvent alarmRegular = registerBypass("alarm.regularSiren");
    public static SoundEvent alarmClassic = registerBypass("alarm.classic");
    public static SoundEvent alarmBank = registerBypass("alarm.bankAlarm");
    public static SoundEvent alarmBeep = registerBypass("alarm.beepSiren");
    public static SoundEvent alarmContainer = registerBypass("alarm.containerAlarm");
    public static SoundEvent alarmSweep = registerBypass("alarm.sweepSiren");
    public static SoundEvent alarmStrider = registerBypass("alarm.striderSiren");
    public static SoundEvent alarmAirRaid = registerBypass("alarm.airRaid");
    public static SoundEvent alarmNostromo = registerBypass("alarm.nostromoSiren");
    public static SoundEvent alarmEas = registerBypass("alarm.easAlarm");
    public static SoundEvent alarmAPCPass = registerBypass("alarm.apcPass");
    public static SoundEvent alarmRazorTrain = registerBypass("alarm.razortrainHorn");
    public static SoundEvent soyuzed = registerBypass("alarm.soyuzed");
    public static SoundEvent metalStep = registerBypass("step.metal");
    public static SoundEvent iron = registerBypass("step.iron");
    public static SoundEvent ironLand = registerBypass("step.iron_land");
    public static SoundEvent ironJump = registerBypass("step.iron_jump");
    public static SoundEvent poweredStep = registerBypass("step.powered");

    public static SoundEvent lambdaCore = registerBypass("music.recordlambdacore");
    public static SoundEvent sectorSweep = registerBypass("music.recordsectorsweep");
    public static SoundEvent vortalCombat = registerBypass("music.recordvortalcombat");
    public static SoundEvent glass = registerBypass("music.transmission");

    public static SoundEvent metalBlock = registerBypass("step.metalBlock");

    public static SoundEvent[] geigerSounds;


    public static void init() {


        assemblerOperate = register("block.assembleroperate");
        fel = register("block.fel");
        pressOperate = register("block.pressoperate");
        laserBang = register("weapon.laserBang");
        blockDebris = register("block.debris");
        syringeUse = register("item.syringe");
        sparkShoot = register("weapon.sparkShoot");
        b92Reload = register("weapon.b92Reload");
        techBleep = register("item.techBleep");
        techBoop = register("item.techBoop");
        reactorStart = register("block.reactorStart");
        reactorStop = register("block.reactorStop");
        chemplantOperate = register("block.chemplantOperate");
        potatOSRandom = register("potatos.random");
        weaponSpinDown = register("weapon.spindown");
        weaponSpinUp = register("weapon.spinup");
        sawShoot = register("weapon.sawShoot");
        rpgShoot = register("weapon.rpgShoot");
        reloadTurret = register("weapon.reloadTurret");
        rifleShoot = register("weapon.rifleShoot");
        defabShoot = register("weapon.defabShoot");
        flamethrowerIgnite = register("weapon.flamethrowerIgnite");
        flamethrowerShoot = register("weapon.flamethrowerShoot");
        tauShoot = register("weapon.tauShoot");
        oldExplosion = register("entity.oldExplosion");
        ciwsSpindown = register("weapon.ciwsSpindown");
        ciwsSpinup = register("weapon.ciwsSpinup");
        ciwsFiringLoop = register("weapon.ciwsFiringLoop");
        planeShotDown = register("entity.planeShotDown");
        bombWhistle = register("entity.bombWhistle");
        planeCrash = register("entity.planeCrash");
        missileTakeoff = register("weapon.missileTakeOff");
        bomberSmallLoop = register("entity.bomberSmallLoop");
        bomberLoop = register("entity.bomberLoop");
        stingerLockon = register("weapon.stingerLockOn");
        trainHorn = register("alarm.trainhorn");
        bombDet = register("entity.bombDet");
        rocketTakeoff = register("entity.rocketTakeoff");
        silencerShoot = register("weapon.silencerShoot");
        GunConfiguration.RSOUND_LAUNCHER = rpgReload = register("weapon.rpgReload");
        GunConfiguration.RSOUND_GRENADE = reloadGrenade = register("weapon.hkReload");
        GunConfiguration.RSOUND_SHOTGUN = reloadShotgun = register("weapon.shotgunReload");
        GunConfiguration.RSOUND_MAG = reloadMag = register("weapon.magReload");
        GunConfiguration.RSOUND_RIFLE = reloadRifle = register("");
        GunConfiguration.RSOUND_REVOLVER = reloadRevolver = register("weapon.revolverReload");
        GunConfiguration.RSOUND_FATMAN = fatmanReload = register("weapon.fatmanReload");
        boatWeapon = register("weapon.boat");
        ricochet = register("weapon.ricochet");
        grenadeBounce = register("weapon.gBounce");
        alarmGambit = register("alarm.gambit");
        revolverShoot = register("weapon.revolverShoot");
        heavyShoot = register("weapon.heavyShoot");
        schrabidiumShoot = register("weapon.schrabidiumShoot");
        revolverShootAlt = register("weapon.revolverShootAlt");
        hkShoot = register("weapon.hkShoot");
        shotgunShoot = register("weapon.shotgunShoot");
        shottyShoot = register("weapon.shottyShoot");
        uziShoot = register("weapon.uziShoot");
        calShoot = register("weapon.calShoot");
        lacunaeShoot = register("weapon.lacunaeShoot");
        fatmanShoot = register("weapon.fatmanShoot");
        osiprShoot = register("weapon.osiprShoot");
        zomgShoot = register("weapon.zomgShoot");
        jetpackTank = register("item.jetpackTank");
        nullTau = register("misc.nullTau");
        immolatorIgnite = register("weapon.immolatorIgnite");
        immolatorShoot = register("weapon.immolatorShoot");
        defabSpinup = register("weapon.defabSpinup");
        cryolatorShoot = register("weapon.cryolatorShoot");
        singFlyby = register("weapon.singFlyby");
        osiprCharging = register("weapon.osiprCharging");
        leverActionReload = register("weapon.leverActionReload");
        follyOpen = register("weapon.follyOpen");
        follyReload = register("weapon.follyReload");
        follyClose = register("weapon.follyClose");
        follyFire = register("weapon.follyFire");
        follyBuzzer = register("weapon.follyBuzzer");
        follyAquired = register("weapon.follyAquired");
        chopperDrop = register("entity.chopperDrop");
        crateBreak = register("block.crateBreak");
        itemUnpack = register("item.unpack");
        centrifugeOperate = register("block.centrifugeOperate");
        buttonNo = register("block.buttonNo");
        buttonYes = register("block.buttonYes");
        railgunFire = register("block.railgunFire");
        railgunOrientation = register("block.railgunOrientation");
        railgunCharge = register("block.railgunCharge");
        shutdown = register("block.shutdown");
        broadcast1 = register("block.broadcast1");
        broadcast2 = register("block.broadcast2");
        broadcast3 = register("block.broadcast3");
        geiger1 = register("item.geiger1");
        geiger2 = register("item.geiger2");
        geiger3 = register("item.geiger3");
        geiger4 = register("item.geiger4");
        geiger5 = register("item.geiger5");
        geiger6 = register("item.geiger6");
        lockOpen = register("block.lockOpen");
        pinBreak = register("item.pinBreak");
        pinUnlock = register("item.pinUnlock");
        lockHang = register("block.lockHang");
        vaultScrapeNew = register("block.vaultScrapeNew");
        vaultThudNew = register("block.vaultThudNew");
        minerOperate = register("block.minerOperate");
        missileAssembly2 = register("block.missileAssembly2");
        sonarPing = register("block.sonarPing");
        radawayUse = register("item.radaway");
        gasmaskScrew = register("item.gasmaskScrew");
        spray = register("item.spray");
        repair = register("item.repair");
        nullChopper = register("misc.nullChopper");
        chopperCharge = register("entity.chopperCharge");
        nullCrashing = register("misc.nullCrashing");
        chopperDamage = register("entity.chopperDamage");
        nullMine = register("misc.nullMine");
        openDoor = register("block.openDoor");
        closeDoor = register("block.closeDoor");
        bang = register("weapon.bang");
        slice = register("weapon.slice");
        kaping = register("weapon.kapeng");
        pipePlaced = register("block.pipePlaced");
        tesla = register("weapon.tesla");
        cybercrab = register("entity.cybercrab");
        osiprReload = register("weapon.osiprReload");
        soyuzReady = register("block.soyuzReady");
        soyuzTakeOff = register("entity.soyuzTakeoff");
        chime = register("alarm.chime");
        deagleShoot = register("weapon.deagleShoot");
        tauChargeLoop2 = register("weapon.tauChargeLoop2");
        chopperFlyingLoop = register("entity.chopperFlyingLoop");
        chopperCrashingLoop = register("entity.chopperCrashingLoop");
        chopperMineLoop = register("entity.chopperMineLoop");
        lacunaeSpinup = register("weapon.lacunaeSpinup");
        lacunaeSpindown = register("weapon.lacunaeSpindown");
        teslaShoot = register("weapon.teslaShoot");
        flamerReload = register("weapon.flamerReload");
        stop = register("weapon.stop");
        bonk = register("weapon.bonk");
        glauncher = register("weapon.glauncher");
        hksShoot = register("weapon.hksShoot");
        vice = register("item.vice");
        screm = register("block.screm");
        upgradePlug = register("item.upgradePlug");
        tauChargeLoop = register("weapon.tauChargeLoop");
        quadroReload = register("weapon.quadroReload");
        fstbmbStart = register("weapon.fstbmbStart");
        fstbmbPing = register("weapon.fstbmbPing");
        sauerGun = register("weapon.sauergun");
        ducc = register("entity.ducc");
        whack = register("weapon.whack");
        turbofanOperate = register("block.turbofanOperate");
        slicer = register("entity.slicer");
        megaquacc = register("entity.megaquacc");
        chainsaw = register("weapon.chainsaw");
        battery = register("item.battery");
        rocketFlame = register("weapon.rocketFlame");
        ballsLaser = register("weapon.ballsLaser");
        dartShoot = register("weapon.dartShoot");
        gluonStart = register("weapon.gluonstart");
        gluonLoop = register("weapon.gluonloop");
        gluonEnd = register("weapon.gluonend");
        gluonHit = register("weapon.gluonhit");
        jetpack = register("weapon.jetpack");
        mukeExplosion = register("weapon.mukeExplosion");
        crucibleStart = register("weapon.crucible_start");
        crucibleEnd = register("weapon.crucible_end");
        crucibleSwing = register("weapon.crucible_swing");
        crucibleLoop = register("weapon.crucible_loop");
        jsg_reload0 = register("weapon.jsg_reload0");
        jsg_reload1 = register("weapon.jsg_reload1");
        mob_gib = register("weapon.mob_gib");
        blood_splat = register("weapon.blood_splat");
        hit_dirt = register("weapon.hit_dirt");
        hit_metal = register("weapon.hit_metal");
        hit_flesh = register("weapon.hit_flesh");
        vomit = register("entity.vomit");
        chekhov_fire = register("turret.chekhov_fire");
        jeremy_fire = register("turret.jeremy_fire");
        jeremy_reload = register("turret.jeremy_reload");
        richard_fire = register("turret.richard_fire");
        howard_fire = register("turret.howard_fire");
        howard_reload = register("turret.howard_reload");
        rbmk_explosion = register("block.rbmk_explosion");
        rbmk_az5_cover = register("block.rbmk_az5_cover");
        chungus_lever = register("block.chungusLever");
        dflash = register("weapon.dFlash");
        cough = register("player.cough");
        ufoBeam = register("entity.ufoBeam");
        ufoBlast = register("entity.ufoBlast");
        transitionSealOpen = register("block.door.transitionseal");
        siloopen = register("block.door.siloopen");
        siloclose = register("block.door.siloclose");
        garage = register("block.door.garage");
        garage_stop = register("block.door.garagestop");
        door_spinny = register("block.door.lever");
        wgh_big_start = register("block.door.wgh_big_start");
        wgh_big_stop = register("block.door.wgh_big_stop");
        wgh_start = register("block.door.wgh_start");
        wgh_stop = register("block.door.wgh_stop");
        alarm6 = register("block.door.alarm6");
        qe_sliding_shut = register("block.door.qe_sliding_shut");
        qe_sliding_opened = register("block.door.qe_sliding_opened");
        qe_sliding_opening = register("block.door.qe_sliding_opening");
        hatch_open = register("block.door.hatch_open");
        sliding_seal_open = register("block.door.sliding_seal_open");
        sliding_seal_stop = register("block.door.sliding_seal_stop");

        geigerSounds = new SoundEvent[]{geiger1, geiger2, geiger3, geiger4, geiger5, geiger6};
    }

    public static SoundEvent register(String name) {
        SoundEvent e = new SoundEvent(new ResourceLocation(RefStrings.MODID, name));
        e.setRegistryName(name);
        ALL_SOUNDS.add(e);
        return e;
    }

    public static SoundEvent registerBypass(String name) {
        SoundEvent e = new SoundEvent(new ResourceLocation(RefStrings.MODID, name));
        e.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(e);
        return e;
    }

}
