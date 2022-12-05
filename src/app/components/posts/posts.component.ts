import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post.model';
import { PostsService } from 'src/app/service/posts.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  postArry: Post[];
  constructor(private postsService: PostsService) { }

  ngOnInit(): void {
    this.postsService.getAllPosts().subscribe({
      next: (data) =>{
        this.postArry = data;
      },
      error: (error) =>{
        console.log(error);
      },
      complete: () =>{
        
      }
    })
  }

}
