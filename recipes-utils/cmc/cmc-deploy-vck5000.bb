SUMMARY = "Simple cmc application deploy"
SECTION = "PETALINUX/apps"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/cmc-deploy:"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:versal = ".*"

RDEPENDS:${PN} = "libmetal"
XILINX_RELEASE_VERSION="2021.1"
CMCPATH="/proj/yocto/${XILINX_RELEASE_VERSION}/cmc/vck5000-versal/cmc_latest"
SRC_URI = "file://${CMCPATH} file://cmc-start.sh"

inherit update-rc.d 

INITSCRIPT_NAME = "cmc-start.sh"
INITSCRIPT_PARAMS = "start 99 S ."

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${CMCPATH}/cmc ${D}${bindir}
	install -m 0755 ${WORKDIR}/cmc-start.sh ${D}${sysconfdir}/init.d/cmc-start.sh
}

FILES:${PN} += "${bindir}/cmc ${sysconfdir}/init.d/cmc-start.sh"
SIGGEN_EXCLUDE_SAFE_RECIPE_DEPS += "cmc-deploy-vck5000->libmetal"
