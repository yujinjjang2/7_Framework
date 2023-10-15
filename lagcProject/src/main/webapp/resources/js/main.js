function travleImgDetail(imgNo)
{
	const imagePath = document.getElementById("imagePath_"+imgNo);
	const imageName = document.getElementById("imageName_"+imgNo);
	
	alert(imgNo);
	alert(imagePath.value);
	alert(imageName.value);
	
	let obj = {};
	obj.imageNo = imgNo;
	obj.imagePath = imagePath.value;
	obj.imageName = imageName.value;
	
	let aa = window.showModalDialog("aa.html", obj ,"dialogHeight:800px; dialogWidth:1000px");
}

function travleImgDel(imgNo)
{
    let obj = {};
    obj.imgNo = imgNo;
    
    // POST 방식
    fetch("/selectMember", { // K:V 형식으로 작성해야 함
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(imgNo) // JS객체 형태 : { K : V }
    })
    .then(resp => resp.json()) // 응답 객체를 자바스크립트 객체 형태로 파싱하는것
    .then(data => {
        console.log(data); // javascript 객체
    })
    .catch( err => console.log(err) );
}