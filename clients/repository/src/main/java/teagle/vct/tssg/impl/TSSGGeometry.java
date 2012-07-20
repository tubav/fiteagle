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
@XmlRootElement(name = "geometry")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGGeometry extends TSSGObject implements Geometry, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6530481017122746148L;

	protected static TSSGCache<TSSGGeometry> cache = new TSSGCache<TSSGGeometry>(
			"geometry", new TSSGGeometry[] {});

	protected int x = 20;

	protected int y = 20;

	protected int w = 0;

	protected int h = 0;

	public TSSGGeometry() {
	}

	protected TSSGGeometry(final Geometry geometry) {
		this.x = geometry.getX();
		this.y = geometry.getY();
		this.w = geometry.getW();
		this.h = geometry.getH();
		this.flag = true;
	}

	public TSSGGeometry(final int x, final int y) {
		this.x = x;
		this.y = y;
		this.w = this.h = 150;
	}

	public static TSSGGeometry find(final String id) {
		return TSSGGeometry.cache.find(id);
	}

	public static List<? extends Geometry> list() {
		return TSSGGeometry.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGGeometry persist() {
		return TSSGGeometry.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGGeometry.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGGeometry resolve() {
		return this.id != null ? TSSGGeometry.cache.find(this.id) : this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGGeometryInstance getInstance() {
		return new TSSGGeometryInstance(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#getH()
	 */
	@Override
	public int getH() {
		return this.h;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#getW()
	 */
	@Override
	public int getW() {
		return this.w;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#getX()
	 */
	@Override
	public int getX() {
		return this.x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#getY()
	 */
	@Override
	public int getY() {
		return this.y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#setH(int)
	 */
	@Override
	public void setH(final int h) {
		this.h = h;
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#setW(int)
	 */
	@Override
	public void setW(final int w) {
		this.w = w;
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#setX(int)
	 */
	@Override
	public void setX(final int x) {
		this.x = x;
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Geometry#setY(int)
	 */
	@Override
	public void setY(final int y) {
		this.y = y;
		this.flag = true;
	}

	@XmlRootElement(name = "geometryInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGGeometryInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5831558061750633284L;

		protected TSSGGeometryInstance() {
		}

		protected TSSGGeometryInstance(final TSSGGeometry geometry) {
		}
	}
}
