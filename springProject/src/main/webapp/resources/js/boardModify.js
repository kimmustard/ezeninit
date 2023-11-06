document.addEventListener('click', (e) => {
    console.log(e);

    if (e.target.classList.contains('file-x')) {
        let uuid = e.target.dataset.uuid;
        console.log(uuid);

        removefileToServer(uuid).then(result => {
            if (result == 1) {
                alert('파일삭제 성공!!');
                e.target.closeset('li').remove();
            } else {
                alert('파일삭제 실패...');
            }
        })

        location.reload();
    }

})


async function removefileToServer(uuid) {
    try {
        const url = '/board/file/' + uuid;
        const config = {
            method: 'delete'
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}