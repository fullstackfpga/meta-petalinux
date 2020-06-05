DESCRIPTION = "PetaLinux runX related packages"

inherit packagegroup features_check

REQUIRED_DISTRO_FEATURES = " virtualization vmsep"

RUNX_PACKAGES = " \
	go-build \
	runx \
	socat \
	daemonize \
	packagegroup-petalinux-xen \
	"

RDEPENDS_${PN} = "${RUNX_PACKAGES}"
