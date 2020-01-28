package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.corejsf.Supplier;
import com.corejsf.SupplierManager;

@Named("con")
@SessionScoped
public class ControllerBean implements Serializable{
	@Inject 
	private SupplierManager supManager;
	
	@Inject
	private Supplier editableSupplier;
	
	private boolean editable;
	
	private String searchText;
	

	public List<Supplier> getSuppliers() {
        Supplier[] allSuppliers = supManager.getAll();
        List<Supplier> searchResults = new ArrayList<Supplier>();
        
        if (searchText == null || searchText.equals("")) {
            for (Supplier s : allSuppliers) {
                searchResults.add(s);
            }
        } else {
            for (Supplier s : allSuppliers) {
                if (s.getSupName().toLowerCase().contains(searchText.toLowerCase())) { searchResults.add(s); }
            }
        }
        return searchResults;
    }
	
	public String newUser() {
		return "addUser";
	}
	
	public String search() {
		return null;
	}
	
	public String clearText() {
		searchText = null;
		return null;
	}
	
	public String deleteSupplier(Supplier sup) {
		supManager.remove(sup);
		return null;
	}
	
	public String editSupplier(Supplier sup) {
		this.editableSupplier = sup;
		return "editUser";
	}
	
	public String saveSupplier() {
		supManager.merge(this.editableSupplier);
		return "viewTable";
	}

	public Supplier getEditableSupplier() {
		return editableSupplier;
	}

	public void setEditableSupplier(Supplier editableSupplier) {
		this.editableSupplier = editableSupplier;
	}
	
    public String changeEditable() {
        this.editable = (editable) ? false : true;
        return null;
    }

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
    
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
    
    /*public String edit(Supplier s) {
        editableSupplier = s;
    	editableSupplier.changeEditable();
        return null;
    }*/
}
