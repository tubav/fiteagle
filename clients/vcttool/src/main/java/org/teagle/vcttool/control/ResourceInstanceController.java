/**
 * 
 */
package org.teagle.vcttool.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.teagle.vcttool.view.ConnectionEvent;
import org.teagle.vcttool.view.ConnectionListener;
import org.teagle.vcttool.view.ResourceInstanceListener;
import org.teagle.vcttool.view.ResourceInstanceWidget;
import org.teagle.vcttool.view.VctView;
import org.teagle.vcttool.view.dialogs.ConfigurationDialog;
import org.teagle.vcttool.view.dialogs.MessageDialog;

import teagle.vct.model.ConfigParamAtomic;
import teagle.vct.model.Configuration;
import teagle.vct.model.ModelManager;
import teagle.vct.model.Ptm;
import teagle.vct.model.ResourceInstance;

/**
 * @author sim
 * 
 */
public class ResourceInstanceController implements ResourceInstanceListener,
		ConnectionListener {

	private final ResourceInstance resourceInstance;
	private Ptm ptm;

	private final Map<VctController, ResourceInstanceWidget> views = new HashMap<VctController, ResourceInstanceWidget>();

	public ResourceInstanceController(final ResourceInstance resourceInstance) {
		this.resourceInstance = resourceInstance;

		String ptmName = null;
		final int pos = resourceInstance.getCommonName().indexOf('.');
		if (pos > 0)
			ptmName = resourceInstance.getCommonName().substring(0, pos);

		if (ptmName != null) {
			final List<? extends Ptm> ptms = ModelManager.getInstance()
					.listPtms();
			for (final Ptm ptm : ptms)
				if (ptm.getCommonName().equals(ptmName)) {
					this.ptm = ptm;
					break;
				}
		}
	}

	public ResourceInstance getResourceInstance() {
		return this.resourceInstance;
	}

	public ResourceInstanceWidget getView(final VctController parent) {
		ResourceInstanceWidget view = this.views.get(parent);
		if (view == null) {
			view = new ResourceInstanceWidget(parent.getView(null));
			this.views.put(parent, view);

			// ibo: needed by the Policy Engine
			view.setResourceInstance(this.resourceInstance);

			view.setName(this.resourceInstance.getCommonName());
			view.setState(this.resourceInstance.getState().toString());
			if (this.ptm != null)
				view.setPtmName(this.ptm.getCommonName());

			// ConfigParam params =
			// resourceInstance.getResourceSpec().getConfigurationParameters();
			// if (params instanceof ConfigParamComposite) {
			// for (ConfigParamAtomic atomic : ((ConfigParamComposite)
			// params).getConfigurationParameters()) {
			// if (atomic.getType().startsWith("reference")) {
			// view.addConfigSource(atomic.getCommonName(), atomic, null);
			// }
			// }
			// } else if (params instanceof ConfigParamAtomic) {
			// view.addConfigSource(((ConfigParamAtomic)params).getCommonName(),
			// params, null);
			// }

			final List<? extends ConfigParamAtomic> params = this.resourceInstance
					.getResourceSpec().getConfigurationParameters();
			for (final ConfigParamAtomic atomic : params)
				if (atomic.getType().startsWith("reference"))
					view.addConfigSource(atomic.getCommonName(), atomic, null);

			final String description = this.resourceInstance.getDescription();
			view.setDescription((description != null) && !description.isEmpty() ? description
					: "<no description>");

			view.setData(this);

			view.addConnectionListener(this);
			view.addResourceInstanceListener(this);

			view.setLocation(this.resourceInstance.getGeometry().getX(),
					this.resourceInstance.getGeometry().getX());

			view.pack(true);
			view.layout(true, true);
		}

		return view;
	}

	public void setPtm(final Ptm ptm) {
		this.ptm = ptm;
		for (final ResourceInstanceWidget view : this.views.values())
			view.setPtmName(ptm.getCommonName());
	}

	public void onMoved(final Point position) {
		this.resourceInstance.getGeometry().setX(position.x);
		this.resourceInstance.getGeometry().setY(position.y);
	}

	public void onEditConfig(final VctView vctView) {
		final ConfigurationDialog dialog = new ConfigurationDialog(
				vctView.getShell(), this.resourceInstance.getCommonName());

		final List<? extends Configuration> configurations = this.resourceInstance
				.getConfigurations();
		for (final Configuration config : configurations) {
			final String name = config.getConfigParamAtomic().getCommonName();
			final String type = config.getConfigParamAtomic().getType();
			final String description = config.getConfigParamAtomic()
					.getDescription();

			String defaultValue = config.getValue();
			if ((defaultValue == null) || defaultValue.isEmpty())
				defaultValue = config.getConfigParamAtomic().getDefaultValue();
			if (type.equals("boolean"))
				dialog.addConfigurationParameter(name,
						Boolean.parseBoolean(defaultValue), description);
			else if (type.equals("string") || type.startsWith("int")
					|| type.equals("float"))
				dialog.addConfigurationParameter(name, defaultValue,
						description);
		}

		switch (dialog.show()) {
		case SWT.OK:
			for (final Configuration config : this.resourceInstance
					.getConfigurations()) {
				final String name = config.getConfigParamAtomic()
						.getCommonName();
				final String value = dialog.getConfiguratonParameter(name);
				if (value != null)
					config.setValue(value);
			}

			break;
		case SWT.CANCEL:
			break;
		default:
			break;
		}
	}

	public void onConnectionNew(final ConnectionEvent event) {
		final ResourceInstanceController targetControler = (ResourceInstanceController) event.target
				.getData();

		final Object data = event.sourceLabel.getData();
		if (data == null) {
			for (final VctController vctControler : this.views.keySet())
				if (this.views.get(vctControler) == event.source) {
					targetControler.getResourceInstance().setParentInstance(
							this.resourceInstance);
					break;
				}
		} else {
			final ConfigParamAtomic param = (ConfigParamAtomic) data;
			Configuration config = null;
			for (final Configuration cfg : this.resourceInstance
					.getConfigurations())
				if (cfg.getConfigParamAtomic() == param) {
					config = cfg;
					break;
				}
			if (config == null) {
				config = ModelManager.getInstance().createConfiguration(param);
				this.resourceInstance.addConfiguration(config);
			}

			final String type = param.getType();
			if (type.equals("reference"))
				config.setValue(targetControler.getResourceInstance()
						.getCommonName());
			else if (type.equals("reference-array")) {
				final String[] entries = config.getValue().split(",");
				String added = "";
				String newValue = targetControler.getResourceInstance()
						.getCommonName();
				for (final String entrie : entries) {
					added += "," + entrie;
					if ((null != newValue) && newValue.equals(entrie))
						newValue = null;
				}
				if (newValue != null) {
					added += "," + newValue;
					config.setValue(added);
				}
			}
		}
	}

	public void onConnectionDeleted(final ConnectionEvent event) {
		final ResourceInstanceController targetControler = (ResourceInstanceController) event.target
				.getData();
		final Object data = event.sourceLabel.getData();
		if (data == null) {
			for (final VctController vctControler : this.views.keySet())
				if (this.views.get(vctControler) == event.source)
					targetControler.getResourceInstance().setParentInstance(
							null);
		} else {
			final ConfigParamAtomic param = (ConfigParamAtomic) data;
			final String type = param.getType();
			for (final Configuration config : this.resourceInstance
					.getConfigurations())
				if (config.getConfigParamAtomic() == param) {
					if (param.isReference())
						this.resourceInstance.removeConfiguration(config);
					else if (type.equals("reference-array")) {
						final String[] entries = config.getValue().split(",");
						String added = "";
						final String removeValue = targetControler
								.getResourceInstance().getCommonName();
						for (int i = 0; i < entries.length; i++)
							if (!removeValue.equals(entries[i]))
								added += "," + entries[i];
						if (!added.isEmpty())
							config.setValue(added);
						else
							this.resourceInstance.removeConfiguration(config);
					}
					break;
				}
		}
	}

	public void onHelp(final VctView vctView) {

		final String description = this.resourceInstance.getDescription();
		if (description.equals(""))
			new MessageDialog(vctView.getShell(), "No description available.");
		else
			new MessageDialog(vctView.getShell(), description);

	}

	public void onDelete(final ResourceInstanceWidget widget) {
		for (final VctController vctControler : this.views.keySet())
			if (this.views.get(vctControler) == widget) {
				vctControler.removeResourceInstance(this);
				vctControler.getView(null).removeResourceInstanceWidget(widget);
			}
	}

	public List<Configuration> getReferenceConfigurations() {
		final List<Configuration> configurations = new ArrayList<Configuration>();
		for (final Configuration config : this.resourceInstance
				.getConfigurations())
			if (config.isReference())
				configurations.add(config);

		return configurations;
	}

}
