package com.attijari.processDemandeConge;

import java.sql.Connection;

import com.axemble.vdoc.sdk.document.extensions.BaseDocumentExtension;
import com.axemble.vdoc.sdk.exceptions.PortalModuleException;
import com.axemble.vdoc.sdk.interfaces.IAction;
import com.axemble.vdoc.sdk.interfaces.IConnectionDefinition;
import com.axemble.vdoc.sdk.interfaces.IContext;

public class _3ValidationSupHierarchique extends BaseDocumentExtension
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7501902336230684835L;
	private IContext ctx;
//	private Connection connection;
//	private PreparedStatement st;
	private String btnEnvoyerNS;
	private String btnRefuserNS;
	private String alerteNS;
	private String reliquatSoldeanneEncoursNS;
	private String fragAlerteNS;
	private String commentaireNS;
	@SuppressWarnings("unchecked")
	public IConnectionDefinition<Connection> ConnectionDefinition(String Ref_externe) throws PortalModuleException
	{
		this.ctx = this.getWorkflowModule().getContextByLogin("sysadmin");
		return (IConnectionDefinition<Connection>) getPortalModule().getConnectionDefinition(ctx, Ref_externe);
	}
	
	@Override
	public boolean onAfterLoad()
	{
		// TODO Auto-generated method stub
		fragAlerteNS = getWorkflowInstance().getCatalog().getConfiguration().getStringUserProperty("FRAG_ALERTSUP");
		btnEnvoyerNS = getWorkflowInstance().getCatalog().getConfiguration().getStringUserProperty("VALSUP_BTNVALIDER");
		btnRefuserNS = getWorkflowInstance().getCatalog().getConfiguration().getStringUserProperty("VALSUP_BTNREFUSER");
		reliquatSoldeanneEncoursNS = getWorkflowInstance().getCatalog().getConfiguration().getStringUserProperty("DEMCON_SOLDEANNENCOURSAPMODIF");
		commentaireNS = getWorkflowInstance().getCatalog().getConfiguration().getStringUserProperty("VALSUP_COMM2");
		alerteNS = getWorkflowInstance().getCatalog().getConfiguration().getStringUserProperty("DEMCON_ALERT"); 
		
		float resteConge = (float) getWorkflowInstance().getValue(reliquatSoldeanneEncoursNS);
		if(resteConge<0){
			getResourceController().setMandatory(commentaireNS, true);
			getWorkflowInstance().setValue(alerteNS, "solde n�gatif");
			getResourceController().showBodyBlock(fragAlerteNS, true);
		}
		else{
			getResourceController().setMandatory(commentaireNS, false);
			getWorkflowInstance().setValue(alerteNS, "");
			getResourceController().showBodyBlock(fragAlerteNS, false);
		}
		return super.onAfterLoad();
	}
	
	@Override
	public boolean onAfterSubmit(IAction action)
	{
		// TODO Auto-generated method stub
		try{
			if(action.getName().equals(btnEnvoyerNS)){
//				connection = SingletonConnexionBDD.getSqlSession().getConnectionVDoc().getConnection();
//				String codeVDOC = (String) getWorkflowInstance().getValue("sys_Reference");
//				String req = "update Conge set EtatConge = ? where CodeVdocDemandeConge = ?";
//				st = connection.prepareStatement(req);
//				st.setString(1, "en etat validation rh");
//				st.setString(2, codeVDOC);
//				st.executeUpdate();
			}
			
			if(action.getName().equals(btnRefuserNS)){
//				connection = SingletonConnexionBDD.getSqlSession().getConnectionVDoc().getConnection();
//				String codeVDOC = (String) getWorkflowInstance().getValue("sys_Reference");
//				String req = "update Conge set EtatConge = ? where CodeVdocDemandeConge = ?";
//				st = connection.prepareStatement(req);
//				st.setString(1, "en etat refus rh");
//				st.setString(2, codeVDOC);
//				st.executeUpdate();
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return super.onAfterSubmit(action);
	}
}
