console.log(bnoVal);

async function postCommentToServer(cmtData) {

    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };

        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtPostBtn').addEventListener('click', () => {
    const cmtText = document.getElementById('cmtText').value;
    const cmtWriter = document.getElementById('cmtWriter').innerText;
    if (cmtText == "" || cmtText == null) {
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus();
        return false;
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: cmtWriter,
            content: cmtText
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result => {
            console.log(result);
            if (result == 1) {
                alert('댓글 등록 성공!');
                getCommentList(cmtData.bno);
            }
            document.getElementById('cmtText').value = '';
        })
    }

})

async function spreadCommentListFromServer(bno, page) {
    try {
        const resp = await fetch('/comment/' + bno + '/' + page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//처음 뿌릴때는 첫페이지 값 = 1로 고정
function getCommentList(bno, page = 1) {
    spreadCommentListFromServer(bno, page).then(result => {
        console.log(result);
        if (result.cmtList.length > 0) {
            let div = document.getElementById('cmtListArea');
            //다시 댓글을 뿌릴때 기존 값 삭제 1 page 일 경우에만
            if (page == 1) {
                div.innerHTML = "";
            }

            for (let i = 0; i < result.cmtList.length; i++) {
                let str = `<div id="cmtBody${i}">`;
                str += `<li class="list-group-item" data-cno=${result.cmtList[i].cno} data-writer=${result.cmtList[i].writer} id="cmtBody${i}">`;
                str += `<div>`;
                str += `<div> ${result.cmtList[i].writer} </div>`;
                str += `${result.cmtList[i].content}`;
                str += `</div>`;
                str += `<span class="badge rounded-pill text-bg-primary"> ${result.cmtList[i].modAt} </span>`;
                str += `<div id="cmtBtnContainer">`;
                str += `<button type="button" class="modBtn btn btn-primary" data-content=${result.cmtList[i].content} data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                str += `<button type="button" data-cno=${result.cmtList[i].cno} data-writer="${result.cmtList[i].writer}" class="delBtn btn btn-primary">삭제</button>`;
                str += `</div>`;
                str += `</li>`;
                str += `</div>`;
                div.innerHTML += str;
                document.getElementById('cmtBtnContainer').style.display = 'flex';
            }
            //댓글 페이징 코드
            let moreBtn = document.getElementById('moreBtn');
            //db pgvo + list 같이 가져와야 값을 줄 수 있음.
            if (result.pgvo.pageNo < result.endPage || result.next) {
                moreBtn.style.visibility = 'visible';   //버튼 표시
                moreBtn.dataset.page = page + 1;
            } else {
                moreBtn.style.visibility = 'hidden';    //버튼 숨김
            }

        } else {
            document.getElementById('cmtListArea').innerHTML += "댓글이 없습니다.";
        }

    })




}



async function removeCommentToServer(cno, writer) {
    try {
        const url = '/comment/' + cno + '/' + writer;
        const config = {
            method: 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e) => {
    console.log(e);
    if (e.target.classList.contains('modBtn')) {
        console.log("수정 버튼 클릭");
        let li = e.target.closest('li');
        let conVal = li.dataset.cno;
        let cmtText = e.target.dataset.content;
        let writer = li.dataset.writer;
        console.log(conVal);
        // let cmtText = document.getElementById('cmtTextInput').value;
        //nextSibling() : 같은 부모의 다음 형제 객체를 반환
        // let cmtText = li.quertSelector('.fw-bold').nextSibling;
        console.log(cmtText);

        //위에서 받은 변수를 버튼에 데이터 반영

        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);
        document.getElementById('cmtModBtn').setAttribute('data-content', cmtText);
        document.getElementById('cmtModBtn').setAttribute('data-writer', writer);
        document.getElementById('cmtTextMod').value = cmtText;


    } else if (e.target.id == 'cmtModBtn') {

        let cmtDataMod = {
            cno: e.target.dataset.cno,
            writer: e.target.dataset.writer,
            content: document.getElementById('cmtTextMod').value
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if (parseInt(result)) {
                //모달창 닫기
                document.querySelector('.btn-close').click();
            }
            getCommentList(bnoVal);
        })
    } else if (e.target.id == 'moreBtn') {
        getCommentList(bnoVal, parseInt(e.target.dataset.page));



    } else if (e.target.classList.contains('delBtn')) {
        console.log("삭제 버튼 클릭");
        let cno = e.target.dataset.cno;
        let writer = e.target.dataset.writer;
        console.log(writer);
        removeCommentToServer(cno, writer).then(result => {
            if (result == 1) {
                alert("삭제 성공");
            } else {
                alert("삭제 실패");
            }
            getCommentList(bnoVal);
        })
    }
})

async function editCommentToServer(cmtDataMod) {
    try {
        const url = '/comment/' + cmtDataMod.cno;
        const config = {
            method: 'put',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}