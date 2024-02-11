# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
# 
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-ericksyndrome.git;protocol=ssh;branch=master \
			file://scull_init \
			"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "8c9c6c85178e5b6582510a4fcd80f930973ee0eb"

S = "${WORKDIR}/git"

inherit module
inherit update-rc.d


#MODULES_INSTALL_TARGET = "install"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "scull_init"

KERNEL_MODULE_AUTOLOAD += "scull"

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
RPROVIDES:${PN} += "kernel-module-scull"
FILES:${PN} += "${INIT_D_DIR}/${INITSCRIPT_NAME}"

do_install:append () {
    install -d ${D}${INIT_D_DIR}
    install -m 0755 ${WORKDIR}/files/${INITSCRIPT_NAME} ${D}${INIT_D_DIR}/${INITSCRIPT_NAME}
}
