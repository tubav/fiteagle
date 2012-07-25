/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Geometry;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 *
 */
@XmlRootElement(name="geometry")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGGeometry extends TSSGObject implements Geometry, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6530481017122746148L;

	protected static TSSGCache<TSSGGeometry> cache = new TSSGCache<TSSGGeometry>("geometry", new TSSGGeometry[]{});
	
	protected int x = 20;
	
	protected int y = 20;
	
	protected int w = 0;
	
	protected int h = 0;
	
	public TSSGGeometry() {
	}

	protected TSSGGeometry(Geometry geometry) {
		x = geometry.getX();
		y = geometry.getY();
		w = geometry.getW();
		h = geometry.getH();
		flag = true;
	}
	
	public TSSGGeometry(int x, int y)
	{
		this.x = x;
		this.y = y;
		w = h = 150;
	}
	
	public static TSSGGeometry find(String id) {
		return cache.find(id);
	}
	
	public static List<? extends Geometry> list() {
		return cache.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGGeometry persist() {
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGGeometry resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGGeometryInstance getInstance() {
		return new TSSGGeometryInstance(this);
	}	

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#getH()
	 */
	@Override
	public int getH() {
		return h;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#getW()
	 */
	@Override
	public int getW() {
		return w;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#getY()
	 */
	@Override
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#setH(int)
	 */
	@Override
	public void setH(int h) {
		this.h = h;
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#setW(int)
	 */
	@Override
	public void setW(int w) {
		this.w = w;
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#setX(int)
	 */
	@Override
	public void setX(int x) {
		this.x = x;
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Geometry#setY(int)
	 */
	@Override
	public void setY(int y) {
		this.y = y;
		flag = true;
	}

	@XmlRootElement(name="geometryInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGGeometryInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5831558061750633284L;

		private int x;
		private int y;
		private int w;
		private int h;
		
		protected TSSGGeometryInstance() {
		}
		
		protected TSSGGeometryInstance(TSSGGeometry geometry) {
			x = geometry.x;
			y = geometry.y;
			w = geometry.w;
			h = geometry.h;		
		}
	}
}
