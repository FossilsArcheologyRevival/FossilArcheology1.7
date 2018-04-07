package fossilsarcheology.server.item.variant;

import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;

import java.util.Locale;

public enum DinosaurBoneType {
	TRICERATOPS,
	VELOCIRAPTOR,
	TYRANNOSAURUS,
	PTEROSAUR,
	PLESIOSAUR,
	MOSASAURUS,
	STEGOSAURUS,
	DILOPHOSAURUS,
	BRACHIOSAURUS,
	SPINOSAURUS,
	COMPSOGNATHUS,
	ANKYLOSAURUS,
	PACHYCEPHALOSAURUS,
	DEINONYCHUS,
	GALLIMIMUS,
	LIOPLEURODON,
	ALLOSAURUS,
	SARCOSUCHUS,
	CERATOSAURUS,
	DRYOSAURUS,
	THERIZINOSAURUS,
	PARASAUROLOPHUS,
	CONFUCIUSORNIS,
	ICTHYOSAURUS,
	HENODUS,
	DODO,
	GASTORNIS,
	KELENKEN,
	PHORUSRHACOS,
	TITANIS,
	MAMMOTH,
	SMILODON,
	QUAGGA,
	ELASMOTHERIUM;

	public static DinosaurBoneType get(PrehistoricEntityType prehistoric) {
		return DinosaurBoneType.valueOf(prehistoric.name().toUpperCase(Locale.ENGLISH));
	}

	public static PrehistoricEntityType getEntity(DinosaurBoneType bone) {
		return PrehistoricEntityType.valueOf(bone.name().toUpperCase(Locale.ENGLISH));
	}

	public String getResourceName() {
		return this.name().toLowerCase(Locale.ENGLISH);
	}
}
