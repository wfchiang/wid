package org.wfchiang.wid.core.enumeration.string;

import io.swagger.v3.oas.models.media.StringSchema;
import org.wfchiang.wid.core.enumeration.EnumerationContext;
import org.wfchiang.wid.core.exception.WidEnumerationException;

import java.util.*;

public class DictionaryFixedLengthStringEnumerator implements StringEnumerator {

    private final List<String> defaultCharList = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
    private final List<String> charList;
    private final int stringLength;
    private String prevInstance = null;

    public DictionaryFixedLengthStringEnumerator (int stringLength) {
        // Set stringLength
        if (stringLength < 0) {
            throw new IllegalArgumentException("Invalid stringLength: " + stringLength);
        }
        this.stringLength = stringLength;

        // Set charList
        this.charList = this.defaultCharList;
    }

    public DictionaryFixedLengthStringEnumerator (int stringLength, List<String> charList) {
        // Set stringLength
        if (stringLength < 0) {
            throw new IllegalArgumentException("Invalid stringLength: " + stringLength);
        }
        this.stringLength = stringLength;

        // Set charList
        if (charList == null || charList.size() == 0) {
            throw new IllegalArgumentException("charList cannot be null or empty");
        }
        if (false == charList.stream().map(x->(x!=null&&x.length()==1)).reduce(true, (x,y)->(x&&y)).booleanValue()) {
            throw new IllegalArgumentException("Invalid charList");
        }
        this.charList = charList;
    }

    @Override
    public Set<String> enumerate(StringSchema stringSchema, EnumerationContext enumerationContext) {
        if (stringSchema == null || enumerationContext == null) {
            throw new IllegalArgumentException("Invalid stringSchema or invalid enumerationContext");
        }

        Set<String> enuSet = null;
        if (this.hasNext()) {
            String nextInstance;
            if (this.prevInstance == null) {
                nextInstance = this.getFirstInstance();
            }
            else {
                nextInstance = new String(this.prevInstance);
                for (int i = this.stringLength-1 ; i >= 0 ; i--) {
                    String nextChar = this.increaseChar(nextInstance.substring(i,i+1));
                    nextInstance = nextInstance.substring(0, i) + nextChar + nextInstance.substring(i+1);
                    if (!nextChar.equals(this.charList.get(0))) {
                        break;
                    }
                }
            }
            enuSet = new HashSet<>();
            enuSet.add(nextInstance);
            this.setPrevInstance(nextInstance);
        }

       return enuSet;
    }

    public boolean isValidInstance (String s) {
        if (s == null || s.length() != this.stringLength) {
            return false;
        }
        List<String> cList = new ArrayList();
        for (Character c : s.toCharArray()) {
            cList.add(String.valueOf(c));
        }
        return cList.stream().map(x->this.charList.contains(String.valueOf(x))).reduce(true, (x,y)->(x&&y));
    }

    public String getFirstInstance () {
        DefaultFixedLengthStringEnumerator defaultFixedLengthStringEnumerator
                = new DefaultFixedLengthStringEnumerator(this.stringLength, this.charList.get(0));
        return defaultFixedLengthStringEnumerator.enumerate(new StringSchema(), new EnumerationContext()).iterator().next();
    }

    public String getLastInstance () {
        DefaultFixedLengthStringEnumerator defaultFixedLengthStringEnumerator
                = new DefaultFixedLengthStringEnumerator(this.stringLength, this.charList.get(this.charList.size()-1));
        return defaultFixedLengthStringEnumerator.enumerate(new StringSchema(), new EnumerationContext()).iterator().next();
    }

    private String increaseChar (String c) {
        if (!this.charList.contains(c)) {
            throw new WidEnumerationException("Character " + c + " is not in the character-list");
        }
        int indexOf = this.charList.indexOf(c);
        return this.charList.get((indexOf+1)%this.charList.size());
    }

    public boolean hasNext () {
        if (this.prevInstance == null) {
            return true;
        }
        return (!this.getLastInstance().equals(this.prevInstance));
    }

    public List<String> getChatList() {
        return charList;
    }

    public int getStringLength() {
        return stringLength;
    }

    public String getPrevInstance() {
        return prevInstance;
    }

    public void setPrevInstance(String prevInstance) {
        if (prevInstance == null || this.isValidInstance(prevInstance)) {
             this.prevInstance = prevInstance;
        }
        else {
            throw new WidEnumerationException("Not a valid instance: " + prevInstance);
        }
    }
}
