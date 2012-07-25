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
@XmlRootElement(name="cost")
@XmlAccessorType(XmlAccessType.FIELD)
public class TSSGCost extends TSSGObject implements Cost, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8256086011998804579L;

	protected static TSSGCache<TSSGCost> cache = new TSSGCache<TSSGCost>("cost", new TSSGCost[]{});
	
	@XmlElement(name="costAmount")
	private double amount;

	@XmlElement(name="costDenominator")
	private String currency = "";

	public TSSGCost() {
	}

	protected TSSGCost(Cost cost) {
		amount = cost.getAmount();
		currency = cost.getCurrency();
		flag = true;
	}
	
	public static TSSGCost find(String id) {
		return cache.find(id);
	}

	public static List<? extends Cost> list() {
		return cache.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGCost persist() {
		return cache.persist(this);
	}

	@Override
	public void delete() throws RepositoryException {
		cache.delete(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TSSGCost resolve() {
		return id != null ? cache.find(id) : this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TSSGCostInstance getInstance() {
		return new TSSGCostInstance(this);
	}	

	/* (non-Javadoc)
	 * @see teagle.vct.model.Cost#getAmount()
	 */
	@Override
	public double getAmount() {
		return amount;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Cost#getCurrency()
	 */
	@Override
	public String getCurrency() {
		return currency;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Cost#setAmount(double)
	 */
	@Override
	public void setAmount(double amount) {
		this.amount = amount;
		flag = true;
	}

	/* (non-Javadoc)
	 * @see teagle.vct.model.Cost#setCurrency(java.lang.String)
	 */
	@Override
	public void setCurrency(String currency) {
		this.currency = currency;
		flag = true;
	}

	@XmlRootElement(name="costInstance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TSSGCostInstance implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5751580631201913138L;

		@XmlElement(name="costAmount")
		private String amount;
		
		@XmlElement(name="costDenominator")
		private String currency = "";
		
		protected TSSGCostInstance() {
		}
		
		protected TSSGCostInstance(TSSGCost cost) {
			this.amount = String.valueOf((long)cost.amount);
			this.currency = cost.currency;
		}
		
	}

	public static void main(String[] args) throws RepositoryException {
		TSSGCost cost = new TSSGCost();
		cost.setAmount(20);
		cost.setCurrency("Yen");
		
		cost = cost.persist();
		
		cost.setAmount(30);
		
		cost = cost.persist();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cost.delete();
	}
}
