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
        alert("댓글을 입력해주세요.")
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
                alert('댓글 등록 성공');
                // 댓글 뿌리기
                getCommentList(bnoVal);
            }
            document.getElementById('cmtText').value = '';
        })
    }
})

async function spreadCommentListFromServer(bno) {
    try {
        const resp = await fetch('/comment/' + bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno) {
    spreadCommentListFromServer(bno).then(result => {
        console.log(result.length);
        if (result.length > 0) {
            let div = document.getElementById('cmtListArea');
            div.innerHTML = "";


            for (let i = 0; i < result.length; i++) {
                let str = `<div id="cmtBody${i}">`;
                str += `<div class="card border-primary mb-3" style="max-width: 20rem;">`;
                str += `<div class="card-header">`;
                str += `${result[i].writer}`;
                str += `<button type="button" class="modBtn" data-num=${i} data-cno=${result[i].cno} data-writer=${result[i].writer} data-content=${result[i].content}>수정</button>`;
                str += `<button type="button" class="delBtn" data-cno=${result[i].cno} data-writer=${result[i].wirter}>삭제</button></div> `;
                str += `<div class="card-body">`;
                str += `<h4 class="card-title"> ${result[i].content}</h4>`;
                str += `<div id="modContainer${i}">`;
                str += `</div>`;
                str += `<p class="card-text"> ${result[i].regAt}</p>`;
                str += `</div>`;
                str += `</div>`;
                str += `</div>`;
                str += `<br>`;
                div.innerHTML += str;
            }
        } else {
            document.getElementById('cmtListArea').innerHTML = "댓글이 없어요.";
        }

    })
}


document.addEventListener('click', (e) => {
    console.log(e);
    if (e.target.classList.contains('modBtn')) {
        console.log('수정버튼 클릭');
        let num = e.target.dataset.num;
        console.log(num);
        let cno = e.target.dataset.cno;
        let writer = e.target.dataset.writer;

        let div = document.getElementById(`modContainer${num}`);
        div.innerHTML = '';
        let str = `<input id="2cmtModBtn${num}" type="text"><button type="button" class="cmtModBtn" id="cmtModBtn${num}">수정하기</button>`;
        div.innerHTML += str;
        let content = document.getElementById(`2cmtModBtn${num}`).value;
        console.log(content);

        document.getElementById(`cmtModBtn${num}`).setAttribute('data-num', num);
        document.getElementById(`cmtModBtn${num}`).setAttribute('data-cno', cno);
        document.getElementById(`cmtModBtn${num}`).setAttribute('data-writer', writer);
        document.getElementById(`cmtModBtn${num}`).setAttribute('data-content', content);


    } else if (e.target.classList.contains('cmtModBtn')) {
        let cmtDataMod = {
            bno: bnoVal,
            cno: e.target.dataset.cno,
            writer: e.target.dataset.writer,
            content: e.target.dataset.content
        };


        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if (result == 1) {
                alert("수정 성공");
            } else {
                alert("수정 실패");
            }
            getCommentList(bnoVal);
        })


    } else if (e.target.classList.contains('delBtn')) {
        console.log('삭제버튼 클릭');
        let cno = e.target.dataset.cno;
        let writer = e.target.dataset.writer;
        removeCommentToServer(cno, writer).then(result => {
            if (result == 1) {
                alert("삭제 성공");
            } else {
                alert("삭제 실패")
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
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
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