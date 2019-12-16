
public class ConstantDomainPDDLFunctions {
	
	public String getPredicates() {
		return " (:predicates \r\n" + 
				"	(available-subject ?subj - subject ?s - student) \r\n" + 
				"	(free ?s - student) \r\n" + 
				"	(pass-degree ?subj - subject ?s - student) \r\n" + 
				"	(enrollment ?s - student ?subj - subject) \r\n" + 
				"	(done-Theme ?t - Theme ?subj -subject ?s - student)\r\n" + 
				"	(done-subject-LA ?subj - subject ?s - student)\r\n" + 
				"	(not-done-LA ?oa - LA ?subj - subject ?s - student)\r\n" + 
				"	(not-approved ?subj - subject ?s - student)\r\n" + 
				"	(isPartOfSubtheme ?oa - LA ?subt - subtheme)\r\n" + 
				"	(isPartOfTheme ?subt - subtheme ?t - Theme)\r\n" + 
				"	(isPartOfSubject ?t - Theme ?subj - subject)\r\n" + 
				"	(KindResourceLO ?oa - LA ?eq - resource)\r\n" + 
				"	(not-has-reqs ?oa - LA)\r\n" + 
				"	(has-reqs ?oa - LA ?req - LO)\r\n" + 
				"	(has-multiple-reqs ?oa - LA ?req - LO)\r\n" + 
				"	(done ?oa - LA)\r\n" + 
				"\r\n" + 
				" )\r\n\n";
	}
	
	public String getFunctions() {
		return " (:functions\r\n" + 
				"	(credits-subject ?subj - subject)\r\n" + 
				"	(total-credits-subject-gain ?s - student)\r\n" + 
				"	(available-credits ?s - student)\r\n" + 
				"	(score ?req - LO ?s - student)\r\n" + 
				"	(quantity-resource ?eq - resource)\r\n" + 
				"	(valueLA ?oa - LA)\r\n" + 
				"	(mingrade ?subj - subject)\r\n" + 
				"	(DurationLA ?oa - LA)\r\n" + 
				"	(maxgrade-subtheme ?subt - subtheme)\r\n" + 
				"	(amount-in-subtheme ?oa - LA)\r\n" + 
				"\r\n" + 
				" )\r\n\n";
	}
	
	public String getLAWithNoReqs(){
		return "(:durative-action CHOOSE-LA-nothasreqs\r\n" + 
				"			:parameters (?s - student ?oa - LA ?subt - subtheme ?t - Theme ?subj - subject ?eq - resource)\r\n" + 
				"			:duration (= ?duration (DurationLA ?oa))\r\n" + 
				"			:condition (and\r\n" + 
				"			                (at start (free ?s))\r\n" + 
				"			                (at start (enrollment ?s ?subj))\r\n" + 
				"			                (at start (not-done-LA ?oa ?subj ?s))\r\n" + 
				"			                (at start (isPartOfSubtheme ?oa ?subt))\r\n" + 
				"			                (at start (isPartOfTheme ?subt ?t))\r\n" + 
				"			                (at start (isPartOfSubject ?t ?subj))\r\n" + 
				"			                (at start (KindResourceLO ?oa ?eq))\r\n" + 
				"			                (at start (> (quantity-resource ?eq) 0))\r\n" + 
				"			                (at start (not-has-reqs ?oa))\r\n" + 
				"			                (at start (> (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                )\r\n" + 
				"			:effect (and\r\n" + 
				"			                (at start (not(free ?s)))\r\n" + 
				"			                (at start (decrease (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (increase (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (not (not-done-LA ?oa ?subj ?s)))\r\n" + 
				"			                (at end (increase (score ?subt ?s) (valueLA ?oa)))\r\n" + 
				"			                (at end (free ?s))\r\n" + 
				"			                (at end (decrease (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                (at end (done ?oa))\r\n" + 
				"			        )\r\n" + 
				"			)\r\n\n" ;
	}
	
	public String getLAWitReqSubthemes(){
		
		return "(:durative-action CHOOSE-LA-hasreqsSubtheme\r\n" + 
				"			:parameters (?s - student ?oa - LA ?subt - subtheme ?t - Theme ?subj - subject ?eq - resource ?req - LO)\r\n" + 
				"			:duration (= ?duration (DurationLA ?oa))\r\n" + 
				"			:condition (and\r\n" + 
				"			                (at start (free ?s))\r\n" + 
				"			                (at start (enrollment ?s ?subj))\r\n" + 
				"			                (at start (not-done-LA ?oa ?subj ?s))\r\n" + 
				"			                (at start (isPartOfSubtheme ?oa ?subt))\r\n" + 
				"			                (at start (isPartOfTheme ?subt ?t))\r\n" + 
				"			                (at start (isPartOfSubject ?t ?subj))\r\n" + 
				"			                (at start (KindResourceLO ?oa ?eq))\r\n" + 
				"			                (at start (> (quantity-resource ?eq) 0))\r\n" + 
				"			                (at start (has-reqs ?oa ?req))\r\n" + 
				"			                (at start (> (score ?req ?s) (amount-in-subtheme ?oa)))\r\n" + 
				"			                (at start (> (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                )\r\n" + 
				"			:effect (and\r\n" + 
				"			                (at start (not(free ?s)))\r\n" + 
				"			                (at start (decrease (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (increase (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (not (not-done-LA ?oa ?subj ?s)))\r\n" + 
				"			                (at end (increase (score ?subt ?s) (valueLA ?oa)))\r\n" + 
				"			                (at end (free ?s))\r\n" + 
				"			                (at end (decrease (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                (at end (done ?oa))\r\n" + 
				"			        )\r\n" + 
				"			)\r\n\n";
	}
	
	public String getLAWitReqLA(){
		
		return "(:durative-action CHOOSE-LA-hasreqsLA\r\n" + 
				"			:parameters (?s - student ?oa - LA ?subt - subtheme ?t - Theme ?subj - subject ?eq - resource ?req - LA)\r\n" + 
				"			:duration (= ?duration (DurationLA ?oa))\r\n" + 
				"			:condition (and\r\n" + 
				"			                (at start (free ?s))\r\n" + 
				"			                (at start (enrollment ?s ?subj))\r\n" + 
				"			                (at start (not-done-LA ?oa ?subj ?s))\r\n" + 
				"			                (at start (isPartOfSubtheme ?oa ?subt))\r\n" + 
				"			                (at start (isPartOfTheme ?subt ?t))\r\n" + 
				"			                (at start (isPartOfSubject ?t ?subj))\r\n" + 
				"			                (at start (KindResourceLO ?oa ?eq))\r\n" + 
				"			                (at start (> (quantity-resource ?eq) 0))\r\n" + 
				"			                (at start (has-reqs ?oa ?req))\r\n" + 
				"			                (at start (done ?req))\r\n" + 
				"			                (at start (> (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                )\r\n" + 
				"			:effect	(and\r\n" + 
				"			                (at start (not(free ?s)))\r\n" + 
				"			                (at start (decrease (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (increase (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (not (not-done-LA ?oa ?subj ?s)))\r\n" + 
				"			                (at end (increase (score ?subt ?s) (valueLA ?oa)))\r\n" + 
				"			                (at end (free ?s))\r\n" + 
				"			                (at end (decrease (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                (at end (done ?oa))\r\n" + 
				"			        )\r\n" + 
				"			)\r\n\n";
	}

	public String getALWithMultipleLAReq(){
	
		return "(:durative-action CHOOSE-LA-hasreqs-multipleLA2\r\n" + 
				"			:parameters (?s - student ?oa - LA ?subt - subtheme ?t - Theme ?subj - subject ?eq - resource ?req1 - LA ?req2 - LA)\r\n" + 
				"			:duration (= ?duration (DurationLA ?oa))\r\n" + 
				"			:condition (and\r\n" + 
				"			                (at start (not(= ?req1 ?req2)))\r\n" + 
				"			                (at start (free ?s))\r\n" + 
				"			                (at start (enrollment ?s ?subj))\r\n" + 
				"			                (at start (not-done-LA ?oa ?subj ?s))\r\n" + 
				"			                (at start (isPartOfSubtheme ?oa ?subt))\r\n" + 
				"			                (at start (isPartOfTheme ?subt ?t))\r\n" + 
				"			                (at start (isPartOfSubject ?t ?subj))\r\n" + 
				"			                (at start (KindResourceLO ?oa ?eq))\r\n" + 
				"			                (at start (> (quantity-resource ?eq) 0))\r\n" + 
				"			                (at start (done ?req1))\r\n" + 
				"			                (at start (done ?req2))\r\n" + 
				"			                (at start (has-multiple-reqs ?oa ?req1))\r\n" + 
				"			                (at start (has-multiple-reqs ?oa ?req2))\r\n" + 
				"			                (at start (> (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                )\r\n" + 
				"			:effect	(and\r\n" + 
				"			                (at start (not(free ?s)))\r\n" + 
				"			                (at start (decrease (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (increase (quantity-resource ?eq) 1))\r\n" + 
				"			                (at end (not (not-done-LA ?oa ?subj ?s)))\r\n" + 
				"			                (at end (increase (score ?subt ?s) (valueLA ?oa)))\r\n" + 
				"			                (at end (free ?s))\r\n" + 
				"			                (at end (decrease (maxgrade-subtheme ?subt)(valueLA ?oa)))\r\n" + 
				"			                (at end (done ?oa))\r\n" + 
				"			                )\r\n" + 
				"			)\r\n\n";
			
	}	
	
	public String getSubjectPass() {
		return "(:durative-action take-subject-pass\r\n" + 
				":parameters (?s - student ?subj - subject)\r\n" + 
				":duration (= ?duration 1)\r\n" + 
				":condition (and\r\n" + 
				"	(at start (enrollment ?s ?subj))\r\n" + 
				"	(at start (done-subject-LA ?subj ?s))\r\n" + 
				"	)\r\n\n" + 
				":effect (and\r\n" + 
				"	(at end (not (not-approved ?subj ?s)))\r\n" + 
				"	(at end (increase (total-credits-subject-gain ?s) (credits-subject ?subj)))\r\n" + 
				"	(at end (not (available-subject ?subj ?s)))\r\n" + 
				"	(at end (pass-degree ?subj ?s))\r\n" + 
				"	)\r\n" + 
				")\r\n" + 
				"\r\n" + 
				")\r\n" + 
				";;END\r\n"; 
				
	}

}