package teagle.vct.model.editor;

/**
 * Base class of a generic rule definition for resources and connections.
 * There is a teagle.vct.library.RuleImpl that derives from this and that is 
 * very similar to a Configuration (at least when looked at using reflection
 * -- that is it also has a static editor field and it only contains a set of 
 * primary fields like a Configuration class). This split is needed because 
 * classes from the model do not reference GUI (i.e. SWT) types. In principle
 * there could be another "concrete" Rule class that has an GWTEditor for 
 * example.
 * 
 * The meaning of the fields is still more or less not fixed, except that 
 * the body_text contains the rule itself, and the others are metadata.  
 */
public class Rule {
	public String kind;
	public String description;
	public String lang;
	public String body_text;
}
