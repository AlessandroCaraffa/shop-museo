const defaultBtn = document.querySelector('#default-btn');
const customBtn = document.querySelector('#custom-btn');
const cancelBtn = document.querySelector('#cancel-btn i');
const img = document.querySelector('#display_image img');
let regExp = /[0-9a-zA-Z\^\&\'\@\{\}\[\]\,\$\!\-\#\(\)\.\%\+\~\_]+$/;

defaultBtn.addEventListener('change', function() {
	const file = this.files[0];
	if (file) {
		const reader = new FileReader();
		reader.onload = function() {
			const result = reader.result;
			img.src = result;
			gsap.from(img, {
				opacity: 0,
				delay: 0.1,
				duration: 0.35
			});
		};
		cancelBtn.addEventListener('click', function() {
			img.src = '';
		});
		reader.readAsDataURL(file);
	}
	if (this.value) {
		let valueStore = this.value.match(regExp);
	}
});


