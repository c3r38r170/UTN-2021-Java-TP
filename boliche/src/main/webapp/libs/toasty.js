'use strict';

let baseURL="https://unpkg.com/egalink-toasty.js@1.5.5//dist/sounds/"
var toast = new Toasty({enableSounds:true,sounds: {
	info: baseURL+"info/1.mp3",
	success: baseURL+"success/1.mp3",
	warning: baseURL+"warning/1.mp3",
	error: baseURL+"error/1.mp3",
}});