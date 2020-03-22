package com.alnt.platform.base.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;



public class BusObjTypeMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799902777242508587L;

	private static final Map<String, BusObjTypeMapping> BUSOBJ = new LinkedHashMap<String, BusObjTypeMapping>();

	public static final BusObjTypeMapping POLICY = new BusObjTypeMapping( "Policy","com.alnt.policyengine.domain.Policy","com.alnt.policyengine.domain.PolicyType");
	public static final BusObjTypeMapping MITIGATION_CONTROL = new BusObjTypeMapping( "MitigationControl","com.alnt.policyengine.domain.MitigationControl","com.alnt.policyengine.domain.MitigationControlType");
	public static final BusObjTypeMapping RULE_SET = new BusObjTypeMapping( "RuleSet","com.alnt.policyengine.domain.RuleSet","com.alnt.policyengine.domain.RuleSetType");
	public static final BusObjTypeMapping RULE = new BusObjTypeMapping( "Rule","com.alnt.policyengine.domain.Rule","com.alnt.policyengine.domain.RuleType");
	public static final BusObjTypeMapping RISK = new BusObjTypeMapping( "Risk","com.alnt.policyengine.domain.Risk","com.alnt.policyengine.domain.RiskType");
	
    public static BusObjTypeMapping getInstance(final String busObj) {
        return BUSOBJ.get(busObj);
    }
    

    private String busObj;
    private String clazz;
    private String typeClazz;

    public BusObjTypeMapping() {
        //do nothing
    }

    public BusObjTypeMapping(final String busObj, final String clazz, final String typeClazz) {
        this.clazz = clazz;
        this.typeClazz = typeClazz;
        setBusObj(busObj);
    }

    public String getBusObj() {
        return busObj;
    }

    public String getClazz() {
        return clazz;
    }
    
    public String getTypeClazz() {
        return typeClazz;
    }
    

	private void setBusObj(final String busObj) {
        this.busObj = busObj;
        if (!BUSOBJ.containsKey(busObj)) {
        	BUSOBJ.put(busObj, this);
        }
    }
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busObj == null) ? 0 : busObj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusObjTypeMapping other = (BusObjTypeMapping) obj;
		if (busObj == null) {
			if (other.busObj != null)
				return false;
		} else if (!busObj.equals(other.busObj))
			return false;
		return true;
	}
 

}
