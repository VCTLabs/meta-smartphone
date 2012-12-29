DESCRIPTION = "Properitary firmware binaries needed for the Samsung Galaxy Nexus smartphone"
LICENSE = "Properitary"

ANDROID_VERSION = "4.1.2_r1"
DEVICE_TUNA_NAME = "android_device_samsung_tuna-android-${ANDROID_VERSION}"
HARDWARE_TI_OMAP4XXX = "android_hardware_ti_omap4xxx-android-${ANDROID_VERSION}"

LIC_FILES_CHKSUM = " \
    file://${WORKDIR}/LICENSE.broadcom;md5=38f6effa6031a775065b10f8943a6efb \
    file://${WORKDIR}/${DEVICE_TUNA_NAME}/README.mms144_ts;md5=dbe38b2af8d17a91e28d0a8f3363a8ad \
    file://${WORKDIR}/${HARDWARE_TI_OMAP4XXX}/MODULE_LICENSE_BSD_APL2;md5=d41d8cd98f00b204e9800998ecf8427e \
"

PR = "r5"

COMPATIBLE_MACHINES = "tuna"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "bcm4330-firmware"

SRC_URI = " \
  https://dl.google.com/dl/android/aosp/broadcom-maguro-jzo54k-8b0d7637.tgz;name=brcm \
  https://github.com/webOS-ports/android_device_samsung_tuna/archive/android-${ANDROID_VERSION}.zip;name=device_tuna;downloadfilename=${DEVICE_TUNA_NAME}.zip \
  https://github.com/webOS-ports/android_hardware_ti_omap4xxx/archive/android-4.1.2_r1.zip;name=hardware_ti_omap4xxx;downloadfilename=${HARDWARE_TI_OMAP4XXX}.zip \
"
S = "${WORKDIR}"

SRC_URI[brcm.md5sum] = "d6e4adf484ac72077015779615f9e59c"
SRC_URI[brcm.sha256sum] = "3e2769eab377ffa4b1a3b35a0d2a49ae12efb371e0d17e8c75e8dd541086af67"
SRC_URI[device_tuna.md5sum] = "5ffe5a52a4fd9b79b5f29ec2696dc354"
SRC_URI[device_tuna.sha256sum] = "5d5d179d5f20ef02bc0426ce59f2320214921cd1bd6a65bc46c95fa9866fd37b"
SRC_URI[hardware_ti_omap4xxx.md5sum] = "ec041fa02d79edd2506d97bf67f665e3"
SRC_URI[hardware_ti_omap4xxx.sha256sum] = "4dd9e35c075f16d21c01e0dfcf458cd5b9277eb8ae3164375a68d46fcbd63cce"

unpack_broadcom_license() {
    head -n 233 extract-broadcom-maguro.sh | tail -n 218 > LICENSE.broadcom
}

python do_unpack() {
    bb.build.exec_func('base_do_unpack', d)
    bb.build.exec_func('unpack_broadcom_license', d)
}

FIRMARE_PATH = "/lib/firmware"

do_install() {
    tail -n +269 extract-broadcom-maguro.sh | tar zxv
    install -d ${D}${FIRMARE_PATH}
    install -m 0644 ${WORKDIR}/vendor/broadcom/maguro/proprietary/bcm4330.hcd ${D}${FIRMARE_PATH}

    install -m 0644 ${WORKDIR}/${DEVICE_TUNA_NAME}/bcmdhd.cal ${D}${FIRMARE_PATH}
    install -m 0644 ${WORKDIR}/${DEVICE_TUNA_NAME}/mms144_ts_rev31.fw ${D}${FIRMARE_PATH}
    install -m 0644 ${WORKDIR}/${DEVICE_TUNA_NAME}/mms144_ts_rev32.fw ${D}${FIRMARE_PATH}

    install -m 0644 ${WORKDIR}/${HARDWARE_TI_OMAP4XXX}/domx/Ducati_binary/etc/firmware/ducati-m3.bin ${D}${FIRMARE_PATH}/ducati-m3.bin
}

# We're differentiate here between files which are redistributable without acknowleding
# any third party license. Files which can be freely redistributes should go into the
# ${PN} package and all other into ${PN}-nonfree

PACKAGES = "${PN}-nonfree ${PN}"
FILES_${PN}-nonfree = " \
    ${FIRMARE_PATH}/bcm4330.hcd \
"
FILES_${PN} = " \
    ${FIRMARE_PATH}/mms144_ts_rev31.fw \
    ${FIRMARE_PATH}/mms144_ts_rev32.fw \
    ${FIRMARE_PATH}/bcmdhd.cal \
    ${FIRMARE_PATH}/ducati-m3.bin \
"
