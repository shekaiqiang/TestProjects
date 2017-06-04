function sayHello(){
  sayHello(null);
}
function sayHello(name){
  if(name==null){
    println("---js:欢迎你!请注册java会员");
  }else{
    println(name+",独立JAVA社区欢迎你---js");
  }
}