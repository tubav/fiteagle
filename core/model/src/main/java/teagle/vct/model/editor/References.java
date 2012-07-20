package teagle.vct.model.editor;

/**
 * Base class for defining allowed/required references between resources. Each
 * field in subclasses of References should have the type of a resource, and
 * its name will be used for references in the resulting booking request. 
 * 
 * This design is influenced by the FOKUS PTM's accepted reference syntax. It 
 * is currently unclear how references of the same type but with different 
 * names should be expressed, for instance.
 */
public class References {
}
