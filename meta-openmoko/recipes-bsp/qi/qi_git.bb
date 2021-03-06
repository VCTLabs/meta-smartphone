require qi.inc

PROVIDES = "virtual/bootloader"

do_configure_prepend() {
  sed -i 's#\(IMAGE = .(IMAGE_DIR)/\)qi-\(.(CPU)-.(BUILD_VERSION)\)#\1qi-jffs2-\2#g' ${S}/Makefile
  sed -i 's#\(UDFU_IMAGE = .(IMAGE_DIR)/\)qi-\(.(CPU)-.(BUILD_VERSION).udfu\)#\1qi-jffs2-\2#g' ${S}/Makefile
}

SRC_URI_append = "\
  file://0003-Revert-gta02-gta01-change-kernel-parameters-to-boot-.patch \
"
