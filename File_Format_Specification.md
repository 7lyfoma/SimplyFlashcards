# File Format Specification For Flash Card files

Metadata is stored at the top of the file in pairs seperated by |
The end of metadata is signalled by a semicolon

>Name|French Basics  
>;  

FlashCards are stored in txt files formatted in the following way

>Front Face (Question)  
>Back Face (Answer)  
>Front Face Image File Path [Optional]  
>Back Face Image File Path [Optional]  
>Semicolon signifying end of flashcard  

E.g.

>Always  
>Toujours  
>;  

>What is the name of this organelle?  
>Nucleus  
>C:\Users\ExampleUser\Pictures\Biology\Nucleus.jpg"  
>;  