/**
 * 
 */

var filterXSS=function(str){
    var results="";
    try{
        HTMLParser(he.unescape(str,{strict:true}), {
          start: function(tag, attrs, unary ) {
	        	if(tag=="script"||tag=="style"||tag=="link"||tag=="iframe"||tag=="frame"||tag=="flash"||tag=="form"||tag=="base"){
	        		return ;
	        	}  
	            results += "<" + tag;
	            for ( var i = 0,length=attrs.length; i <length; i++ ){
	            	if(xssoptions.attrfiler.contains(attrs[i].name)){
	            		continue;
	            	}
	            	if(attrs[i].name=="style"){
	            		results += " " + attrs[i].name + '="' + xssoptions.stylefilter.filter(attrs[i].escaped)+ '"';
	            	}else{
	            		results += " " + attrs[i].name + '="' + attrs[i].escaped + '"';
	            	}
	            }
	            results += unary?"/":"";
	            results += ">";
          },
          end: function( tag ) {
        	  results += "</" + tag + ">";
          },
          chars: function( text ) {
        	  results += text;
          },
          comment: function( text ) {
        	  results += "<!--" + text + "-->";
          }
        });    
    }catch(e){
        console.log(e);
    }finally{
    	return results;
    }
}

var xssoptions={
		attrfiler:{
			blacklist:["onerror","onclick","onload","onchange","onblur","onabort","ondblclick","onfocus","onkeydown","onkeypress","onkeyup","onmousedown","onmousemove","onmouseout","onmouseover","onmouseup","onreset","onresize","onselect","onsubmit","onunload"],
			contains:function(attr){
				var _attr=attr.toLowerCase();
				for(i in this.blacklist){
					if(this.blacklist[i]==_attr)return true;
				}
				return false;
			}
		},
		stylefilter:{
			blacklist:["background","expression","javascript"],
			contains:function(attr){
				var _attr=attr.toLowerCase();
				for(i in this.blacklist){
					if(_attr.indexOf(this.blacklist[i])>-1)return true;
				}
				return false;
			},
			filter:function(style){
				var _style="";
				var tem_style=style.split(";");
				for(j in tem_style){
					if(this.contains(tem_style[j])){
						continue;
					}
					_style+=tem_style[j]+";";
				}
				return _style;
			},
		}
}

var filterConten=function(str){
    var results="";
    try{
        HTMLParser(he.unescape(str,{strict:true}), {
          start: function(tag, attrs, unary ) {
	            results += "<" + tag;
	            for ( var i = 0,length=attrs.length; i <length; i++ ){
            		results += " " + attrs[i].name + '="' + attrs[i].escaped + '"';
	            }
	            results += unary?"/":"";
	            results += ">";
          },
          end: function( tag ) {
        	  results += "</" + tag + ">";
          },
          chars: function( text ) {
        	  results += text;
          },
          comment: function( text ) {
        	  results += "<!--" + text + "-->";
          }
        });    
    }catch(e){
        console.log(e);
    }finally{
    	return results;
    }
}