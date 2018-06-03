package com.vix.core.utils.json.felxjson.former;

import flexjson.transformer.AbstractTransformer;

public class FelxLazyInitObjectTransformer  extends AbstractTransformer {


    @Override
	public void transform(Object value) {
        //getContext().writeQuoted(null);
        getContext().write(null);
    }

    /*  implements ObjectFactory
    public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass) {
        try {
            return simpleDateFormatter.parse( value.toString() );
        } catch (ParseException e) {
            throw new JSONException(String.format( "Failed to parse %s with %s pattern.", value, simpleDateFormatter.toPattern() ), e );
        }
    }*/
}
