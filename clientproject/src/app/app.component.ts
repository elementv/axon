import { Component } from '@angular/core';
import {Http} from '@angular/http';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'my-app',
  template: `
      <div class="form-group">
        <label>username</label>
        <input [(ngModel)]="username" #ctrl="ngModel" required>
		<label>Password</label>
        <input [(ngModel)]="password" #ctrl="ngModel" required>
      </div>
	  <button (click)="onLoginClicked()">Log in</button>
	  <hr>
      <div class="form-group">
        <label>Text of the blog post to create</label>
        <input [(ngModel)]="blogpostTextToCreate" #ctrl="ngModel" required>
      </div>
	   <button (click)="onClickCreateBlogPost()">Create a blog post</button>
	   <hr>
	   <div class="form-group">
        <label>Text of the own blog post to update</label>
        <input [(ngModel)]="blogpostTextToUpdate" #ctrl="ngModel" required>
		<label>ID of the own blog post to update</label>
		<input [(ngModel)]="blogpostIDToUpdate" #ctrl="ngModel" required>
      </div>
	   <button (click)="onClickUpdateBlogPost()">Update a blog post</button>
	   <hr>
	  <div class="form-group">
        <label>ID of the blog post to delete</label>
        <input [(ngModel)]="blogpostIdToDelete" #ctrl="ngModel" required>
      </div>
      <button (click)="onClickDeleteBlogPost()">Delete a blog post</button>
	  <hr>
	  <div class="form-group">
        <label>ID of the own blog post to get</label>
        <input [(ngModel)]="blogpostIDToGet" #ctrl="ngModel" required>
      </div>
	   <button (click)="onClickGetOwnBlogPostByID()">Get an own blog post by id</button>
	   <hr>
	  <button (click)="onClickGetAllOwnBlogPosts()">Get all own blog posts</button>
	  <button (click)="onClickGetAllBlogPosts()">Get all blog posts</button>
	   <hr>
	 `
})
export class AppComponent { 
   username: string = '';
   password: string = '';

   blogpostTextToCreate: string = 'Test text';
   
   blogpostIdToDelete: string = '';
	blogpostTextToUpdate: string = '';
	blogpostIDToUpdate: string = '';
	blogpostIDToGet: string = '';
	
   constructor(private http: HttpClient) {
   }

   onClickCreateBlogPost(): void {
	  this.http.post('/blogposts/', {text: this.blogpostTextToCreate}).subscribe();
   }
   
   onClickUpdateBlogPost(): void {
	  this.http.put('/blogposts/' + this.blogpostIDToUpdate, {text: this.blogpostTextToUpdate, id: this.blogpostIDToUpdate}).subscribe();
   }
   
   onClickDeleteBlogPost(): void {
	  this.http.delete('/blogposts/' + this.blogpostIdToDelete).subscribe();
   }
   
   onLoginClicked(): void {
	   this.http.post('/loginresource/', {username: this.username, password: this.password}).subscribe();
   }
   
    onClickGetOwnBlogPostByID(): void {
	  this.http.get('/blogposts/' + this.blogpostIDToGet).subscribe();
   }
   
   onClickGetAllOwnBlogPosts(): void {
	  this.http.get('/blogposts?own=true').subscribe();
   }
   
   onClickGetAllBlogPosts(): void {
	  this.http.get('/blogposts?own=false').subscribe();
   }
}
