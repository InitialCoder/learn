/**
 * 富文本防御XSS  过滤器
 * 包含ie9 复制预览图片强制图片的宽度为20%，并且禁止class样式
 * 包含edge 浏览器复制预览图片时会有两种图片提交成功的处理
 * 包含粘贴本系统的文件服务器的全路径图片的处理 
 * 
 * tips: 去除he的unescapse，同时服务器图文分离工具ImageUtil也去除unescapse by：吴培良  date：20181224 
 */

var filterXSS=function(str){
    var results="";
	var isEdge=xssoptions.getBrowsType()=='edge'?true:false;
    try{
        HTMLParser(str, {
          start: function(tag, attrs, unary ) {
        	  	var _tag=tag.toLowerCase();
	        	if(_tag=="script"||_tag=="style"||_tag=="link"||_tag=="iframe"||_tag=="frame"||_tag=="flash"||_tag=="form"||_tag=="base"){
	        		return ;
	        	} 
	        	//edge 浏览器下复制预览图片时会包含两张的处理
	        	if(_tag=="img"&&isEdge&&xssoptions.containSpecialClass.filter(attrs)){
	        		
	        		return ;
	        	}
	        	
	            results += "<" + _tag;
	            for ( var i = 0,length=attrs.length; i <length; i++ ){
	            	if(xssoptions.attrfiler.contains(attrs[i].name)){
	            		continue;
	            	}
	            	if(_tag=="img"){
	            		if(attrs[i].name=="style"){
		            		results += " " + attrs[i].name + '="width:20%;"';
		            		continue;
		            	}else if(attrs[i].name=="class"||attrs[i].name=="width"||attrs[i].name=="height"){
		            		continue;
		            	}
	            		//图片的src处理
	            		if(attrs[i].name=="src"){
	            			var _escaped=attrs[i].escaped;
	            			results+=" "+ attrs[i].name +'="'+_escaped.substring(_escaped.indexOf('kindeditor')-1)+'"';
	            			continue;
	            		}
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

/**
 * 属性黑名单 attrfiler
 * style样式黑名单 stylefilter
 * 获取ie  edge 浏览器类型 getBrowsType
 * class样式黑名单  containSpecialClass
 * 
 */
var xssoptions={
		attrfiler:{
			blacklist:["onerror","onclick","onload","onchange","onblur","onabort","ondblclick","onfocus","onkeydown","onkeypress","onkeyup","onmousedown","onmousemove","onmouseout","onmouseover","onmouseup","onreset","onresize","onselect","onsubmit","onunload"],
			contains:function(attr){
				var _attr=attr.toLowerCase();
				for(i in this.blacklist){
					if(this.blacklist[i]==_attr){
						return true;
					}
				}
				return false;
			}
		},
		stylefilter:{
			blacklist:["background-image","expression","javascript","behavior"],
			contains:function(attr){
				var _attr=attr.toLowerCase();
				for(i in this.blacklist){
					if(_attr.indexOf(this.blacklist[i])>-1){
						return true;
					}
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
		},
		getBrowsType:function(){
			var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
			var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器  
			// win10 edge
			//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299
			var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器  
			if(isIE){
				return "ie";
			}else if(isEdge){
				return "edge";
			}else{
				return "other";
			}
			
		},
		containSpecialClass:{
			list:['pswp__img'],
			contains:function(attr){
				var _attr=attr.toLowerCase();
				for(j in this.list){
					if(_attr==this.list[j]){
						return true;
					}
				}
				return false;
			},
			filter:function(attrs){
				if(!Array.isArray(attrs)||attrs.length==0){
					return false;
				}
				for(var j in attrs){
					if(attrs[j].name.toLowerCase()=='class'){
						var class_list=attrs[j].escaped.split(' ');
						for(cl in class_list){
							if(this.contains(class_list[j])){
								return true;
							}
						}
					}
				}
				return false;
			}
		}
}

/**
 * 正常的HTML解析
 * 若HTML格式不正确的话将会在格式错误位置向下位移一位继续解析（具体代码在htmlparser异常处理中）
 * 
 * @param html 代码
 */
var filterConten=function(str){
    var results="";
    try{
        HTMLParser(str, {
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

