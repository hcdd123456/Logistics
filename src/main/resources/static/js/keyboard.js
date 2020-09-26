window.addEventListener('keyup',handleKeyup);
window.addEventListener("click",handleClick);

function handleKeyup(event){
    const e = event || window.event || arguments.callee.caller.arguments[0];
    if(!e) return;
    const {key,keyCode} = e;
    this.upload(key);
}
function handleClick() {
    this.upload("左");
}
function upload(value) {
    $.get("/keyboard/add",{value: value},function (result) {
        console.log(result)
    })
}

