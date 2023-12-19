package com.ajar.pagefullstory;

import org.springframework.stereotype.Service;

@Service
public class AllBehold {
	
	public boolean fileEnabled = false;

    public boolean isfileEnabled() {
        return fileEnabled;
    }

    public void setfileEnabled(boolean featureEnabled) {
        this.fileEnabled = featureEnabled;
    }
}
