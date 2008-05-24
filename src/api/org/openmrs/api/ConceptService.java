/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.api;

import java.util.List;
import java.util.Locale;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptNumeric;
import org.openmrs.ConceptProposal;
import org.openmrs.ConceptSet;
import org.openmrs.ConceptWord;
import org.openmrs.Drug;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.db.ConceptDAO;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contains methods pertaining to creating/updating/deleting/retiring 
 * Concepts, Drugs, Concept Proposals, and all other things 'Concept'
 * 
 * Use:
 * <code>
 *   List<Concept> concepts = Context.getConceptService().getAllConcepts();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface ConceptService extends OpenmrsService {
	
	/**
	 * Sets the data access object for Concepts.  The dao is used for 
	 * saving and getting concepts to/from the database
	 * 
	 * @param dao The data access object to use
	 */
	public void setConceptDAO(ConceptDAO dao);

	/**
	 * @deprecated use #saveConcept(Concept)
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void createConcept(Concept concept) throws APIException;

	/**
	 * @deprecated use #saveConcept(Concept)
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void createConcept(ConceptNumeric concept) throws APIException;

	/**
	 * @deprecated use #saveConcept(Concept)
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConcept(Concept concept) throws APIException;

	/**
	 * @deprecated use #saveConcept(Concept)
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConcept(ConceptNumeric concept) throws APIException;
	
	/**
	 * @deprecated use #saveDrug(Drug)
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void createDrug(Drug drug) throws APIException;

	/**
	 * @deprecated use #saveDrug(Drug)
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateDrug(Drug drug) throws APIException;
	
	/**
	 * @deprecated use #purgeConcept(Concept concept)
	 */
	@Authorized({OpenmrsConstants.PRIV_PURGE_CONCEPTS})
	public void deleteConcept(Concept concept) throws APIException;
	
	/**
	 * @deprecated use {@link #retireConcept(Concept, String)}           
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void voidConcept(Concept concept, String reason) throws APIException;
	
	/**
	 * Save or update the given <code>Concept</code> or <code>ConceptNumeric</code> in the database
	 * 
	 * @param Concept concept The <code>Concept</code> or <code>ConceptNumeric</code> to save or update
	 * @return the <code>Concept</code> or <code>ConceptNumeric</code> that was saved or updated
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public Concept saveConcept(Concept concept) throws APIException;
	
	/**
	 * Save or update the given <code>Drug</code> in the database
	 * 
	 * @param Drug drug The Drug to save or update
	 * @return the Drug that was saved or updated
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public Drug saveDrug(Drug drug) throws APIException;
	
	/**
	 * Completely purge a <code>Concept</code> or <code>ConceptNumeric</code> from the database.  
	 * This should not typically be used unless desperately needed.  Most should just be retired.
	 * See {@link #retireConcept(Concept, String)}
	 * 
	 * @param Object conceptOrConceptNumeric The <code>Concept</code> or <code>ConceptNumeric</code> to remove from the system
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_PURGE_CONCEPTS)
	public void purgeConcept(Concept conceptOrConceptNumeric) throws APIException;
	
	/**
	 * Retiring a concept essentially removes it from circulation
	 * 
	 * @param Concept conceptOrConceptNumeric The <code>Concept</code> or <code>ConceptNumeric</code> to retire
	 * @param String reason The retire reason
	 * @return the retired <code>Concept</code> or <code>ConceptNumeric</code>
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_MANAGE_CONCEPTS)
	public Concept retireConcept(Concept conceptOrConceptNumeric, String reason) throws APIException;
	
	/**
	 * Retiring a Drug essentially removes it from circulation
	 * 
	 * @param Drug drug The Drug to retire
	 * @param String reason The retire reason
	 * @throws APIException
	 * @return the retired Drug
	 */
	@Authorized(OpenmrsConstants.PRIV_MANAGE_CONCEPTS)
	public Drug retireDrug(Drug drug, String reason) throws APIException;
	
	/**
	 * Completely purge a Drug from the database.  This should not typically
	 * be used unless desperately needed.  Most Drugs should just be retired.
	 * 
	 * @param Drug drug The Drug to remove from the system
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_PURGE_CONCEPTS)
	public void purgeDrug(Drug drug) throws APIException;
	
	/**
	 * Gets the concept with the given id
	 * 
	 * @param Integer conceptId
	 * @return the matching Concept object
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConcept(Integer conceptId) throws APIException;

	/**
	 * Gets the ConceptAnswer with the given id
	 * 
	 * @param Integer conceptAnswerId
	 * @return the matching ConceptAnswer object
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public ConceptAnswer getConceptAnswer(Integer conceptAnswerId) throws APIException;

	/**
	 * Get the Drug with the given id
	 * 
	 * @param Integer drugId 
	 * @return the matching Drug object
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Drug getDrug(Integer drugId) throws APIException;
	
	/**
	 * Get the ConceptNumeric with the given id
	 * 
	 * @param Integer conceptId The ConceptNumeric id
	 * @return the matching ConceptNumeric object
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public ConceptNumeric getConceptNumeric(Integer conceptId) throws APIException;
	
	/**
	 * Return a Concept class matching the given identifier
	 * @throws APIException
	 * @param i the concept class identifier
	 * @return the matching ConceptClass
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public ConceptClass getConceptClass(Integer conceptClassId) throws APIException;
	
	/**
	 * Return a list of unretired concepts sorted by concept id ascending and 
	 * 
	 * @return a List<Concept> object containing all of the sorted concepts
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getAllConcepts() throws APIException;
	
	/**
	 * Return a list of concepts sorted on sortBy in dir direction (asc/desc)
	 *
	 * @param sortBy The property name to sort by; if null or invalid, concept_id is used.
	 * @param boolean asc true = sort ascending; false = sort descending
	 * @param boolean includeRetired If <code>true</code>, retired concepts will also be returned
	 * @return a List<Concept> object containing all of the sorted concepts
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getAllConcepts(String sortBy, boolean asc, boolean includeRetired) throws APIException;
		
	/**
	 * @deprecated use {@link #getAllConcepts(String, boolean, boolean)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConcepts(String sortBy, String dir) throws APIException;

	/**
	 * Returns a list of concepts matching any part of a concept name
	 * @param String name The search string
	 * @throws APIException
	 * @return a List<Concept> object containing all of the matching concepts
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByName(String name) throws APIException;

	/**
	 * Return a Concept that matches the name exactly
	 * @param String name The search string
	 * @throws APIException
	 * @return the found Concept
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConceptByName(String name) throws APIException;

	/**
	 * Get Concepts by id or name
	 * Note: this just calls other impl methods; no DAO of its own
	 * 
	 * @param String idOrName
	 * @return the found Concept
	 * @deprecated use {@link #getConceptByName(String)}
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConceptByIdOrName(String idOrName) throws APIException;
	
	/**
	 * Get Concept by id or name convenience method
	 * 
	 * @param String conceptIdOrName
	 * @return the found Concept
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getConcept(String conceptIdOrName) throws APIException;
	
	/**
	 * Searches for concepts by the given parameters via the ConceptWord table
	 * 
	 * @param String phrase 
	 * @param List<Locale> locales
	 * @param boolean includeRetired 
	 * @param List<ConceptClass> requireClasses
	 * @param List<ConceptClass> excludeClasses
	 * @param List<ConceptDatatype> requireDatatypes
	 * @param List<ConceptDatatype> excludeDatatypes
	 * @param answersToConcept all results will be a possible answer to this concept
	 * @param int start
	 * @param int size
	 * @return A List<ConceptWord> object containing all matching ConceptWords
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> getConceptWords(String phrase, List<Locale> locales, boolean includeRetired, 
			List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
			List<ConceptDatatype> requireDatatypes,List<ConceptDatatype> excludeDatatypes,
			Concept answersToConcept, Integer start, Integer size) throws APIException;
	
	/**
	 * Convenience method for {@link #getConceptWords(String, List, boolean, List, List, List, List, Integer, Integer)}
	 * 
	 * @param phrase search string
	 * @param locale
	 * @return
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> getConceptWords(String phrase, Locale locale) throws APIException;
	
	/**
	 * @deprecated Use {@link #getConceptWords(String, List, boolean, List, List, List, List, Integer, Integer)
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> findConcepts(String phrase, Locale locale,
			boolean includeRetired) throws APIException;
	
	/**
	 * @deprecated Use {@link #getConceptWords(String, List, boolean, List, List, List, List, Integer, Integer)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> findConcepts(String phrase, Locale locale, boolean includeRetired, 
			List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
			List<ConceptDatatype> requireDatatypes,List<ConceptDatatype> excludeDatatypes) throws APIException;

	/**
	 * @deprecated Use {@link #getConceptWords(String, List, boolean, List, List, List, List, Integer, Integer)
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> findConcepts(String phrase, Locale locale,
			boolean includeRetired, int start, int size) throws APIException;
	
	/**
	 * Return the drug object corresponding to the given name or drugId
	 * 
	 * @param drugNameOrId name or drugId to match exactly on
	 * @return Drug
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Drug getDrug(String drugNameOrId) throws APIException;
	
	/**
	 * Return the drug object corresponding to the given name or drugId
	 * @param String drugId
	 * @throws APIException
	 * @return Drug
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Drug getDrugByNameOrId(String drugId) throws APIException;
	
	/**
	 * @deprecated use {@link ConceptService#getAllDrugs()}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getDrugs() throws APIException;
	
	/**
	 * Return a list of drugs currently in the database that are
	 * not retired
	 * 
	 * @throws APIException
	 * @return a List<Drug> object containing all drugs
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getAllDrugs() throws APIException;
	
	/**
	 * @deprecated Use {@link #getDrugsByConcept(Concept)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getDrugs(Concept concept) throws APIException;
	
	/**
	 * Return a list of drugs associated with the given concept
	 * @throws APIException
	 * @param Concept concept
	 * @return a List<Drug> object containing all matching drugs
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getDrugsByConcept(Concept concept) throws APIException;
	
	/**
	 * Get drugs by concept.  
	 * This method is the utility method that should be used to generically retrieve all Drugs in the system.
	 * 
	 * @param includeRetired If <code>true</code> then the search will include voided Drugs
	 * @return A List<Drug> object containing all matching Drugs
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getAllDrugs(boolean includeRetired);
	
	/**
	 * @deprecated Use {@link #getDrugs(String)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> findDrugs(String phrase, boolean includeVoided) throws APIException;
	
	/**
	 * Find drugs in the system. The string search can match either drug.name or
	 * drug.concept.name
	 * 
	 * @param String phrase 
	 * @param Locale locale
	 * @throws APIException
	 * @return A List<Drug> object containing all Drug matches
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Drug> getDrugs(String phrase) throws APIException;
	
	/**
	 * @param ConceptClass cc
	 * @return Returns all concepts in a given class
	 * @throws APIException 
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByClass(ConceptClass cc) throws APIException;
	
	/**
	 * Return a list of concept classes currently in the database
	 * @throws APIException
	 * @return List<ConceptClass> object with all ConceptClass objects
	 * @deprecated use #getAllConceptClasses(boolean)
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_CLASSES)
	public List<ConceptClass> getConceptClasses() throws APIException;
	
	/**
	 * Return a Concept class matching the given name
	 * 
	 * @param String name
	 * @return ConceptClass
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_CLASSES)
	public ConceptClass getConceptClassByName(String name) throws APIException;
	
	/**
	 * Return a list of concept classes currently in the database
	 * 
	 * @throws APIException
	 * @return List<ConceptClass> object with all ConceptClass objects
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_CLASSES)
	public List<ConceptClass> getAllConceptClasses() throws APIException;
	
	/**
	 * Return a list of concept classes currently in the database
	 * 
	 * @param include retired concept classes in the search results
	 * @throws APIException
	 * @return List<ConceptClass> object with all ConceptClass objects
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_CLASSES)
	public List<ConceptClass> getAllConceptClasses(boolean includeRetired) throws APIException;

	/**
	 * Creates or updates a concept class
	 * @param ConceptClass to create or update
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_MANAGE_CONCEPT_CLASSES)
	public ConceptClass saveConceptClass(ConceptClass cc) throws APIException;

	/**
	 * Purge a ConceptClass
	 * @param ConceptClass to delete
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_PURGE_CONCEPT_CLASSES)
	public void purgeConceptClass(ConceptClass cc) throws APIException;	
	
	/**
	 * Create or update a ConceptDatatype
	 * @param ConceptDatatype to create or update
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPT_DATATYPES})
	public ConceptDatatype saveConceptDatatype(ConceptDatatype cd) throws APIException;
	
	/**
	 * Purge a ConceptDatatype.  This removes the concept datatype from the database completely.
	 * @param ConceptDatatype to purge
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_PURGE_CONCEPT_DATATYPES)
	public void purgeConceptDatatype(ConceptDatatype cd) throws APIException;
	
	/**
	 * Return a list of concept datatypes currently in the database
	 * 
	 * @throws APIException
	 * @return List of ConceptDatatypes
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public List<ConceptDatatype> getAllConceptDatatypes() throws APIException;
	
	/**
	 * Return a list of concept datatypes currently in the database
	 * 
	 * @param includeRetired true/false whether to include the retired datatypes
	 * @throws APIException
	 * @return List of ConceptDatatypes
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public List<ConceptDatatype> getAllConceptDatatypes(boolean includeRetired) throws APIException;
	
	/**
	 * Find concept datatypes that contain the given name string
	 * 
	 * @param name
	 * @return
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public List<ConceptDatatype> getConceptDatatypes(String name) throws APIException;
	
	/**
	 * Return a ConceptDatatype matching the given identifier
	 * @param Integer i
	 * @return ConceptDatatype
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public ConceptDatatype getConceptDatatype(Integer i) throws APIException;
	
	/**
	 * Return a Concept datatype matching the given name
	 * 
	 * @param String name
	 * @return ConceptDatatype
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_DATATYPES)
	public ConceptDatatype getConceptDatatypeByName(String name) throws APIException;
	
	/**
	 * Updates the concept set derived business table for this concept (bursting the concept sets) 
	 * @param concept
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConceptSetDerived(Concept concept) throws APIException;
	
	/**
	 * Iterates over all concepts calling updateConceptSetDerived(concept)
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConceptSetDerived() throws APIException;
	
	/**
	 * @deprecated use {@link #getConceptSetsByConcept(Concept)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptSet> getConceptSets(Concept concept) throws APIException;
	
	/**
	 * Return a list of the concept sets with concept_set matching concept
	 * For example to find all concepts for ARVs, you would do
	 *    getConceptSets(getConcept("ANTIRETROVIRAL MEDICATIONS"))
	 * and then take the conceptIds from the resulting list.
	 * 
	 * @param Concept concept The concept representing the concept set
	 * @return A List<ConceptSet> object containing all matching ConceptSets
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptSet> getConceptSetsByConcept(Concept concept) throws APIException;
	
	/**
	 * @deprecated use {@link #getConceptsByConceptSet(Concept)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsInSet(Concept concept) throws APIException;
	
	/**
	 * Return a List of all concepts within a concept set
	 * @param Concept concept The concept representing the concept set
	 * @return A List<Concept> object containing all objects within the ConceptSet
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByConceptSet(Concept concept) throws APIException;
	
	/**
	 * Find all sets that the given concept is a member of 
	 * @param Concept concept
	 * @throws APIException
	 * @return A List<ConceptSet> object with all parent concept sets
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptSet> getSetsContainingConcept(Concept concept) throws APIException;
	
	/**
	 * @deprecated use {@link #getAllConceptProposals(boolean)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public List<ConceptProposal> getConceptProposals(boolean includeCompleted) throws APIException;
	
	/**
	 * Get a List of all concept proposals
	 * 
	 * @param boolean includeCompleted 
	 * @return a List<ConceptProposal> object of all found ConceptProposals
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public List<ConceptProposal> getAllConceptProposals(boolean includeCompleted) throws APIException;
	
	/**
	 * Get a ConceptProposal by conceptProposalId
	 * 
	 * @param Integer conceptProposalId the concept proposal Id 
	 * @return the found ConceptProposal
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public ConceptProposal getConceptProposal(Integer conceptProposalId) throws APIException;
	
	/**
	 * find matching concept proposals
	 * 
	 * @param String text
	 * @return a List<ConceptProposal> object containing all concept proposals
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public List<ConceptProposal> getConceptProposals(String text) throws APIException;
	
	/**
	 * @deprecated Use #getConceptProposals(String) and then retrieve the Concepts out of the ConceptProposals...
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public List<Concept> findProposedConcepts(String text) throws APIException;
	
	/**
	 * find matching concept proposals and return a list of the proposed concepts
	 * 
	 * @param text
	 * @return
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPT_PROPOSALS)
	public List<Concept> getProposedConcepts(String text) throws APIException;
	

	/**
	 * Searches on given phrase via the concept word table within a sorted list of Locales
	 * 
	 * @param phrase/search/words
	 *            String
	 * @param searchLocales
	 *            ordered List of Locales within which to search
	 * @param includeRetired
	 *            boolean
	 * @param requireClasses
	 *            List<ConceptClass>
	 * @param excludeClasses
	 *            List<ConceptClass>
	 * @param requireDatatypes
	 *            List<ConceptDatatype>
	 * @param excludeDatatypes
	 *            List<ConceptDatatype>
	 * @return
	 * 
	 * @see ConceptService.findConcepts(String,Locale,boolean)
	 */
	@Transactional(readOnly=true)
	@Authorized({"View Concepts"})
	public List<ConceptWord> findConcepts(String phrase, List<Locale> searchLocales, boolean includeRetired, 
			List<ConceptClass> requireClasses, List<ConceptClass> excludeClasses,
			List<ConceptDatatype> requireDatatypes,List<ConceptDatatype> excludeDatatypes);
	
	/**
	 * @deprecated use {@link #saveConceptProposal(ConceptProposal)}
	 */
	@Authorized(OpenmrsConstants.PRIV_ADD_CONCEPT_PROPOSALS)
	public void proposeConcept(ConceptProposal conceptProposal) throws APIException;
	
	/**
	 * Saves/updates/proposes a concept proposal
	 * 
	 * @param ConceptProposal conceptProposal 
	 * @throws APIException
	 * @return the saved/update ConceptProposal object
	 */
	@Authorized({OpenmrsConstants.PRIV_ADD_CONCEPT_PROPOSALS, OpenmrsConstants.PRIV_EDIT_CONCEPT_PROPOSALS})
	public ConceptProposal saveConceptProposal(ConceptProposal conceptProposal) throws APIException;
	
	/**
	 * Removes a concept proposal from the database entirely.
	 * 
	 * @param cp
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_PURGE_CONCEPT_PROPOSALS)
	public void purgeConceptProposal(ConceptProposal cp) throws APIException;
	
	/**
	 * Maps a concept proposal to a concept
	 * 
	 * @param cp
	 * @param mappedConcept
	 * @return the mappedConcept
	 * @throws APIException
	 */
	@Authorized(OpenmrsConstants.PRIV_MANAGE_CONCEPTS)
	public Concept mapConceptProposalToConcept(ConceptProposal cp,
			Concept mappedConcept) throws APIException;
	
	/**
	 * @deprecated use {@link ConceptProposal#rejectConceptProposal()}
	 */
	@Authorized(OpenmrsConstants.PRIV_EDIT_CONCEPT_PROPOSALS)
	public void rejectConceptProposal(ConceptProposal cp) throws APIException;
	
	
	/**
	 * @deprecated use {@link ConceptService#getConceptProposals(String)}
	 */
	@Authorized(OpenmrsConstants.PRIV_ADD_CONCEPT_PROPOSALS)
	public List<ConceptProposal> findMatchingConceptProposals(String text);
	
	/**
	 * @deprecated use {@link #getConceptAnswers(String, Locale, Concept)}
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> findConceptAnswers(String phrase, Locale locale,
			Concept concept, boolean includeRetired) throws APIException;
	
	/**
	 * Finds concepts that are possible value coded answers to concept parameter
	 * 
	 * @param String phrase
	 * @param Locale locale
	 * @param Concept concept the answers to match on
	 * @return List<ConceptWord> object
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<ConceptWord> getConceptAnswers(String phrase, Locale locale,
			Concept concept) throws APIException;
	
	/**
	 * @deprecated use #getConceptsByAnswer(Concept)
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getQuestionsForAnswer(Concept concept) throws APIException;
	
	/**
	 * Returns all possible value-coded answers to a Concept
	 * To navigate in the other direction, i.e., from Concept to its answers use Concept.getAnswers() 
	 * 
	 * @param Concept concept
	 * @return A List<Concept> containing all possible value coded answers to a question
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsByAnswer(Concept concept) throws APIException;

	/**
	 * Finds the previous concept in the dictionary that has the next
	 * lowest concept id
	 * 
	 * @param Concept concept the offset Concept
	 * @return the foundConcept
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getPrevConcept(Concept concept) throws APIException;

	/**
	 * Finds the next concept in the dictionary that has the next
	 * largest concept id
	 * 
	 * @param Concept concept the offset Concept
	 * @return the foundConcept
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Concept getNextConcept(Concept concept) throws APIException;
	
	/**
	 * Get the lowest concept id that is not currently in use
	 * 
	 * @return the next available Id
	 * @throws APIException
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public Integer getNextAvailableId() throws APIException;
	
	/**
	 * Check if the concepts are locked and if so, throw exception during 
	 * manipulation of concept
	 * 
	 * @throws ConceptsLockedException
	 */
	@Transactional(readOnly=true)
	public void checkIfLocked() throws ConceptsLockedException;
	
	/**
	 * Convenience method for finding concepts associated with drugs in formulary.
	 * 
	 * @return A List<Concept> object of all concepts that occur as a Drug.concept.
	 * @throws APIException
	 * 
	 */
	@Transactional(readOnly=true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_CONCEPTS)
	public List<Concept> getConceptsWithDrugsInFormulary() throws APIException;

	/**
	 * Iterates over the words in names and synonyms (for each locale) and updates the concept_word table
	 * @param Concept concept
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConceptWord(Concept concept) throws APIException;

	/**
	 * Iterates over all concepts and calls upddateConceptWords(Concept concept)
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConceptWords() throws APIException;
	
	/**
	 * Iterates over all concepts with conceptIds between <code>conceptIdStart</code>
	 * and <code>conceptIdEnd</code> (inclusive) and calls updateConceptWord(concept)
	 * @param conceptIdStart starts update with this concept_id
	 * @param conceptIdEnd ends update with this concept_id
	 * @throws APIException
	 */
	@Authorized({OpenmrsConstants.PRIV_MANAGE_CONCEPTS})
	public void updateConceptWords(Integer conceptIdStart, Integer conceptIdEnd) throws APIException;
	
}