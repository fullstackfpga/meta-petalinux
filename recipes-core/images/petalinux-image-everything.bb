DESCRIPTION = "OSL image definition for Xilinx boards"
LICENSE = "MIT"

require petalinux-image-full.inc

DEPENDS:append = " protobuf-native libeigen-native python3-setuptools-native"
DEPENDS:append:zynq   = " libeigen"
DEPENDS:append:zynqmp = " libeigen"
DEPENDS:append:versal = " libeigen"

# We include docker (via IMAGE_FEATURES and packagegroup-ocicontainers)
# but also want docker-compose to be available.  Use same switch method.
IMAGE_INSTALL:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'virtualization vmsep', ' python3-docker-compose', '', d)}"

IMAGE_INSTALL:append:zynq = " kernel-devsrc xrt"

VITISAI_DEPENDENCIES = "opencv googletest protobuf-c boost json-c libunwind"
IMAGE_INSTALL:append:zynqmp = " ${VITISAI_DEPENDENCIES} xrt watchdog-init hellopm cppzmq-dev jansson packagegroup-petalinux-som kernel-devsrc kernel-module-dp mosquitto"
IMAGE_INSTALL:append:zynqmp = "${@bb.utils.contains('DISTRO_FEATURES', 'openamp', ' openamp-demo-notebooks', '', d)}"
#IMAGE_INSTALL:append:zynqmp-ev = " gstreamer-vcu-examples gstreamer-vcu-notebooks"
IMAGE_INSTALL:append:zynqmp-dr = " sdfec rfdc rfdc-intr rfdc-read-write rfdc-selftest rfclk"

IMAGE_INSTALL:append:versal = " ${VITISAI_DEPENDENCIES} xrt kernel-devsrc"
IMAGE_INSTALL:append:versal = "${@bb.utils.contains('DISTRO_FEATURES', 'openamp', ' openamp-demo-notebooks', '', d)}"

# ultra96-zynqmp recipes
#IMAGE_INSTALL:append:ultra96 = " sensors96b-overlays-notebooks "
IMAGE_INSTALL:append:ultra96 = "  ultra96-ap-setup ultra96-power-button"
IMAGE_INSTALL:append:ultra96 = " sensor-mezzanine-examples"

IMAGE_INSTALL:append:vck-sc = " power-advantage-tool packagegroup-petalinux-syscontroller packagegroup-petalinux-scweb"

# vpk-sc recipes
IMAGE_INSTALL:append:vpk-sc = " power-advantage-tool packagegroup-petalinux-syscontroller packagegroup-petalinux-scweb"

IMAGE_INSTALL:append = " tree ttf-bitstream-vera packagegroup-core-full-cmdline"

KV260_PACKAGES = " \
	packagegroup-kv260-aibox-reid \
	packagegroup-kv260-defect-detect \
	packagegroup-kv260-nlp-smartvision \
	packagegroup-kv260-smartcam \
	kv260-dpu-benchmark \
	"
#IMAGE_INSTALL:append:k26-kv = " ${KV260_PACKAGES}"
