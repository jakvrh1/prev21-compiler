JAVA            = java
XSLTPROC	= xsltproc
RM              = 'rm' -fr

PHASE		= all
LOG		= all

.PHONY	: %
%	: %.p21
	$(RM) -fr $@.*.xml $@.*.html
	if $(JAVA) -cp ../bin:../src:../lib/antlr-4.9.1-complete.jar prev.Compiler --xsl=../lib/xsl/ --logged-phase=$(LOG) --target-phase=$(PHASE) $< ; then for XML in `'ls' -1 $@.*.xml` ; do $(XSLTPROC) $$XML -o `basename $$XML xml`html; done ; fi

.PHONY  : clean cleanall
clean   :
	$(RM) *~
	$(RM) *xml *html

cleanall :
	$(RM) *mmo *mms