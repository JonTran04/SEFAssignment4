public class App {
    public static void main(String[] args) throws Exception {
        int postID = 1;
        String postTitle = "A Sample Post Title";
        String postBody = "This is the body of the sample post. It needs to be sufficiently long to meet the minimum length requirement. Here is some additional text to ensure the body has at least 250 characters. ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
        String[] postTags = {"sample", "post"};
        String postType = "Difficult";
        String postEmergency = "Highly Needed";
        
        // Instantiate a Post object with the example data
        Post post = new Post(postID, postTitle, postBody, postTags, postType, postEmergency);
        Post post2 = new Post(2, postTitle, postBody, postTags, postType, postEmergency);

        post.addPost();
        post.addComment("This is a sample comment for post 1.");
        post.addComment("This is another sample comment.");
    
        post2.addPost();
        post2.addComment("This is a sample comment for post 2.");
    }
}
