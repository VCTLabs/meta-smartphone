DESCRIPTION = "Niebiee - extremely blue theme for SHR (metapackage)"
SECTION = "x11/data"
RDEPENDS_${PN} += "e-wm-theme-illume-niebiee elementary-theme-niebiee shr-splash-theme-niebiee phoneui-shr-theme-niebiee"
PV = "0.1"
PR = "r4"
inherit allarch
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

ALLOW_EMPTY_${PN} = "1"
