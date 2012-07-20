/**
 * 
 */
package teagle.vct.tssg.impl;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import teagle.vct.model.Cost;
import teagle.vct.model.RepositoryException;

/**
 * @author sim
 * 
 */
@XmlRootElement(name = "cost")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGCost extends TSSGObject implements Cost, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8256086011998804579L;

	protected static TSSGCache<TSSGCost> cache = new TSSGCache<TSSGCost>(
			"cost", new TSSGCost[] {});

	@XmlElement(name = "costAmount")
	private double amount;

	@XmlElement(name = "costDenominator")
	private String currency = "";

	public TSSGCost() {
	}

	protected TSSGCost(final Cost cost) {
		this.amount = cost.getAmount();
		this.currency = cost.getCurrency();
		this.flag = true;
	}

	public static TSSGCost find(final String id) {
		return TSSGCost.cache.find(id);
	}

	public static List<? extends Cost> list() {
		return TSSGCost.cache.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGCost persist() {
		return TSSGCost.cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		TSSGCost.cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGCost resolve() {
		return this.id != null ? TSSGCost.cache.find(this.id) : this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGCostInstance getInstance() {
		return new TSSGCostInstance(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Cost#getAmount()
	 */
	@Override
	public double getAmount() {
		return this.amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Cost#getCurrency()
	 */
	@Override
	public String getCurrency() {
		return this.currency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Cost#setAmount(double)
	 */
	@Override
	public void setAmount(final double amount) {
		this.amount = amount;
		this.flag = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teagle.vct.model.Cost#setCurrency(java.lang.String)
	 */
	@Override
	public void setCurrency(final String currency) {
		this.currency = currency;
		this.flag = true;
	}

	@XmlRootElement(name = "costInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGCostInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5751580631201913138L;

		@XmlElement(name = "costAmount")
		private String amount;

		@XmlElement(name = "costDenominator")
		private String currency = "";

		protected TSSGCostInstance() {
		}

		protected TSSGCostInstance(final TSSGCost cost) {
			this.amount = String.valueOf((long) cost.amount);
			this.currency = cost.currency;
		}

	}

	public static void main(final String[] args) throws RepositoryException {
		TSSGCost cost = new TSSGCost();
		cost.setAmount(20);
		cost.setCurrency("Yen");

		cost = cost.persist();

		cost.setAmount(30);

		cost = cost.persist();

		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		cost.delete();
	}
}
