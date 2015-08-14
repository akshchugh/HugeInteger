package hello;

import java.util.Arrays;
import java.util.function.Predicate;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HugeInteger {
	
	int[] hugeInteger;
	
	
	public HugeInteger(){
		System.out.println("haha");
	}
	
	@RequestMapping(value = "add")
	public String add(@RequestParam(value = "integer") String integer,
			@RequestParam(value = "addTo") String hi){
		int[] hiArr = parse(hi);
		this.hugeInteger = parse(integer);
		ArrayUtils.reverse(hiArr); 
		ArrayUtils.reverse(this.hugeInteger);
		for (int i = 0; i < (hugeInteger.length > hiArr.length ? hiArr.length: hugeInteger.length); i++)
			hugeInteger[i] += hiArr[i];
		ArrayUtils.reverse(hugeInteger);
		return toString();
	}

	@RequestMapping(value = "sub")
	public String sub(@RequestParam(value = "integer") String integer,
			@RequestParam(value = "subFrom") String hi){
		int[] hiArr = parse(hi);
		this.hugeInteger = parse(integer);
		ArrayUtils.reverse(hiArr); 
		ArrayUtils.reverse(this.hugeInteger);
		for (int i = 0; i < (hugeInteger.length > hiArr.length ? hiArr.length: hugeInteger.length); i++)
			hugeInteger[i] -= hiArr[i];
		ArrayUtils.reverse(hugeInteger);
		return toString();
	}
	
	
	@RequestMapping("/isZero")
	public boolean isZero(){
		return Arrays.asList(ArrayUtils.toObject(hugeInteger)).stream()
		.map(t -> t == 0)
		.reduce(true, (x,y) -> x && y);
		
	}
	
	@RequestMapping("/HugeInteger")
	public boolean operations(@RequestParam(value = "integer")String val,
			@RequestParam(value = "operation")String operation,
			@RequestParam(value = "compare", defaultValue = "")String hgInt){
	    this.hugeInteger = parse(val);
	    
	    Predicate<Integer> isZero = t -> {return isZero();};
	    Predicate<String> isEqualTo = t -> {return isEqualTo(t);};
	    Predicate<String> isNotEqualTo = t -> {return isNotEqualTo(t);};
	    Predicate<String> isGreaterThan = t -> {return isGreaterThan(t);};
	    Predicate<String> isLessThan = t -> {return isLessThan(t);};
	    Predicate<String> isGreaterThanOrEqualTo = t -> {return isGreaterThanOrEqualTo(t);};
	    Predicate<String> isLessThanOrEqualTo = t -> {return isLessThanOrEqualTo(t);};
	    
	    
	    switch(operation){
	    case "isZero":
	    	return isZero.test(7);
	    case "isEqualTo":
	    	return isEqualTo.test(hgInt);
	    case "isNotEqualTo":
	    	return isNotEqualTo.test(hgInt);
	    case "isGreaterThan":
	    	return isGreaterThan.test(hgInt);
	    case "isLessThan":
	    	return isLessThan.test(hgInt);
	    case "isGreaterThanOrEqualTo":
	    	return isGreaterThanOrEqualTo.test(hgInt);
	    case "isLessThanOrEqualTo":
	    	return isLessThanOrEqualTo.test(hgInt);
	    }
		return true;
	}
	
	@Override
	@RequestMapping("/toString")
	public String toString(){
		StringBuilder sb = new StringBuilder();
        for(Object obj : hugeInteger) sb.append(obj.toString());
        return sb.toString();
	}
	
	private boolean isEqualTo(String hi){
		if (hi.length() != hugeInteger.length)
			return false;
		for (int i = 0; i < hugeInteger.length; i++)
			if (hugeInteger[i] != hi.charAt(i) - 48)
				return false;
		return true;	
	}
	
	private boolean isNotEqualTo(String hi){
		if (hi.length() != hugeInteger.length)
			return false;
		for (int i = 0; i < hugeInteger.length; i++)
			if (hugeInteger[i] != hi.charAt(i) - 48)
				return false;
		return true;	
	}
	
	private boolean isGreaterThan(String hi){
		while (hi.charAt(0) - 48 == 0)
			hi = hi.substring(1);
		if (hugeInteger.length < hi.length())
			return false;
		for (int i = 0; i < hugeInteger.length; i++)
			if (hugeInteger[i] < hi.charAt(i) - 48)
				return false;
		return true;	
	}
	
	private boolean isLessThan(String hi){
		while (hi.charAt(0) - 48 == 0)
			hi = hi.substring(1);
		if (hugeInteger.length > hi.length())
			return false;
		for (int i = 0; i < hugeInteger.length; i++)
			if (hugeInteger[i] > hi.charAt(i) - 48)
				return false;
		return true;	
	}
	
	private boolean isGreaterThanOrEqualTo(String hi){
		return isGreaterThan(hi) || isEqualTo(hi);
	}
	
	private boolean isLessThanOrEqualTo(String hi){
		return isLessThan(hi) || isEqualTo(hi);
	}
	
	private int[] parse(String hgInt){
		while (hgInt.charAt(0) - 48 == 0)
			hgInt = hgInt.substring(1);
		String[] intArr = hgInt.split("");
		int[] hugeInteger = new int[intArr.length];
		for (int i = 0; i < intArr.length; i++)
			hugeInteger[i] = Integer.parseInt(intArr[i]);
		
		return hugeInteger;
	}
	
}
