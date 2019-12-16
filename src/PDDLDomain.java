
public class PDDLDomain {

	String[] Subjects;
	String[] Themes;
	String[] Activities;
	String[] Resources;
	
	

	public PDDLDomain(String[] subjects, String[] themes, String[] activities, String[] resources) {
		Subjects = subjects;
		Themes = themes;
		Activities = activities;
		Resources = resources;
	}

	public byte[] getPDDLDomain() {

		StringBuilder domain = new StringBuilder();

		ConstantDomainPDDLFunctions pddlFunctions = new ConstantDomainPDDLFunctions();
		domain.append(createDomainHeader());
		domain.append(pddlFunctions.getPredicates());
		domain.append(pddlFunctions.getFunctions());
		domain.append(pddlFunctions.getLAWithNoReqs());
		domain.append(pddlFunctions.getLAWitReqSubthemes());
		domain.append(pddlFunctions.getLAWitReqLA());
		domain.append(pddlFunctions.getALWithMultipleLAReq());
		domain.append(pddlFunctions.getSubjectPass());
		
		return domain.toString().getBytes();

	}

	private String createDomainHeader() {
		
		for (String subject : Subjects) {
			System.out.println(subject);
		}
		
		String header = "(define (domain degree)\r\n" + 
				" (:requirements :durative-actions :typing :fluents :equality)\r\n" + 
				" (:types student resource - object\r\n" + 
				"         subject Theme subtheme LA - LO)\r\n" + 
				" (:constants\r\n" + 
				"        DataStructuresAlgs - subject\r\n" + 
				"\r\n" + 
				"        Trees\r\n" + 
				"        Sorting - Theme\r\n" + 
				"\r\n" + 
				"        Binary\r\n" + 
				"        Red-Black\r\n" + 
				"        Quicksort\r\n" + 
				"        TreeSort - subtheme\r\n" + 
				"\r\n" + 
				"        A1\r\n" + 
				"        A2\r\n" + 
				"        A3\r\n" + 
				"        A4\r\n" + 
				"        A5\r\n" + 
				"        A6\r\n" + 
				"        A7\r\n" + 
				"        A8\r\n" + 
				"        A9\r\n" + 
				"        A10\r\n" + 
				"        A11\r\n" + 
				"        A12 - LA\r\n" + 
				"\r\n" + 
				"        rec0\r\n" + 
				"        rec1\r\n" + 
				"        rec2\r\n" + 
				"        rec3 - resource\r\n" + 
				"\r\n" + 
				" )";

		return header;
	
	}
}
