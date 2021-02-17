//封装方法，根据id来获取元素对象
// function get(id){
function $(id){
    var obj = document.getElementById(id);
    return obj;
}