DESCRIPTION = "Terminal phone for OpenMoko/FSO"
HOMEPAGE = "http://code.google.com/p/thone/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=2c12447f794c304d9cd353f87a432c9e"
AUTHOR = "pike"
RDEPENDS_${PN} = "bash python python-dbus"
PR = "r3"

SRC_URI = "http://thone.googlecode.com/files/${P}.tgz;name=archive \
           file://LICENSE \
"
SRC_URI[archive.md5sum] = "ddf90638ac279b359e9081e0271fe881"
SRC_URI[archive.sha256sum] = "e3eadb050b29385b9ffd2347bcc6cdbc75a681aba93efff4fee954cd6e39bb1e"

S = "${WORKDIR}/usr"

do_install() {
  install -d ${D}/usr
  cp -Ra ${S}/bin ${D}/usr
  cp -Ra ${S}/share ${D}/usr
}
