package teagle.vct.model.editor;

/**
 * This declares the need for a generic list editor to receive
 * the type of objects that are in the list. It is a method and not
 * a constructor because these editors can only be instantiated by
 * reflection (using newInstance()) and passing parameters there is
 * problematic. This is an interface and not an abstract class because,
 * java lacking multiple inheritance, it would limit the ability to 
 * derive an editor from two types, for example ListEditor and 
 * InstantValidationEditor. 
 * 
 * It's debatable whether this really is a real issue for editors. 
 * It is however clearly an issue with configuration hierarchies. For 
 * instance if you have two 'VirtualNode' and 'Router' abstract classes
 * that resource creators are expected to extend, creating a virtual
 * router that runs in a virtual node is not possible by defining a
 * class that extends (subclasses) these two. This is solvable either by 
 * having the two configurations as fields in a new class (containment)
 * or by defining VirtualNode and Router as interfaces. Still, in many 
 * cases abstract classes and subclassing are enough. 
 * 
 * The reason this template parameter needs to be passed is because
 * inferring the parameter of an ArrayList<Something> through reflection
 * is to my knowledge not possible.
 * 
 * @author bvh
 */
public interface List_EditorInterface {
	/**
	 * Sends the collection parameter to the editor. This is called before 
	 * calling load(). 
	 */
	public void setTypeParameter(Class<?> parameter);
}
