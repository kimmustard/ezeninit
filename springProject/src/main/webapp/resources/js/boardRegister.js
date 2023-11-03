document.getElementById('trigger').addEventListener('click', () => {
    document.getElementById('files').click();
})

const regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$");
const regExpImg = new RegExp("\.(jpg|jpeg|png|gif|bmp|JPG|JPEG|PNG)$");
const maxSize = 1024 * 1024 * 20;

function fileValidation(fileName, fileSize) {
    if (!regExpImg.test(fileName)) {
        return 0;
    } else if (regExp.test(fileName)) {
        return 0;
    } else if (fileSize > maxSize) {
        return 0;
    } else {
        return 1;
    }
}

document.addEventListener('change', (e) => {
    if (e.target.id == 'files') {
        document.getElementById('regBtn').disabled = false;

        const fileObject = document.getElementById('files').files;
        console.log(fileObject);

        let div = document.getElementById('fileZone');
        div.innerHTML = '';

        let isOk = 1;
        let ul = `<ul class="list-group">`;
        for (let file of fileObject) {
            let validResult = fileValidation(file.name, file.size);
            isOk *= validResult;
            ul += `<li class="list-group-item d-flex justify-content-between align-items-center">`;
            ul += `<div class="mb-3">`;
            ul += `${validResult ? '<p class="text-primary-emphasis">업로드 가능</p>' : '<p class="text-danger">업로드 불가능</p>'}`;
            ul += `${file.name}</div>`;
            ul += `<span class="badge rounded-pill ${validResult ? 'bg-primary' : 'bg-danger'}">${file.size}</span></li>`;
        }
        ul += `<ul>`;
        div.innerHTML = ul;

        if (isOk == 0) {
            document.getElementById('regBtn').disabled = true;
        }
    }
})