//
//  myTableViewController.m
//  redditApp
//
//  Created by vpalakur on 12/19/14.
//  Copyright (c) 2014 vpalakur. All rights reserved.
//

#import "myTableViewController.h"
#import "myTableViewCell.h"

@interface myTableViewController ()

@end

@implementation myTableViewController

NSMutableArray *cellArray;


- (void)viewDidLoad {
    [super viewDidLoad];
    
    cellArray = [[NSMutableArray alloc] init];
    
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
    
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
    NSError *error;
    //NSLog(@"from tableView Contrlooer %@",_jsonData);
    NSData *data = [self.jsonData dataUsingEncoding:NSUTF8StringEncoding];
    NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data options:0 error:&error];
    NSDictionary *dataDic = json[@"data"];
    NSArray *arr = dataDic[@"children"];
    
    
    for (NSDictionary *dictionary in arr) {
        NSDictionary *subDataDic = dictionary[@"data"];
       
        myTableViewCell *cell = [[myTableViewCell alloc] init];
        cell.title = [subDataDic objectForKey:@"title"];
        cell.author = [subDataDic objectForKey:@"author"];
        cell.date = [subDataDic objectForKey:@"created"];
        cell.score = [subDataDic objectForKey:@"score"];
        
        [cellArray addObject:cell];
        
        //NSLog(@"%@",cell.author);
    }
     
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Return the number of rows in the section.
    return cellArray.count;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    myTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"myIdentifier" forIndexPath:indexPath];
    
    // Configure the cell...
    myTableViewCell *dummy = [cellArray objectAtIndex:indexPath.row];
    cell.titleName.text = dummy.title;
    //NSLog(@"%i",indexPath.row);
    cell.authorName.text = dummy.author;
    //cell.scoreName.text = dummy.score;
    //cell.dateName.text = dummy.date;
    //cell.imageView.image = [UIImage imageNamed:@"reddit_1.png"];
    
    //cell.authorName.text = @"emo mari";
    
    return cell;
}


/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

/*
#pragma mark - Table view delegate

// In a xib-based application, navigation from a table can be handled in -tableView:didSelectRowAtIndexPath:
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    // Navigation logic may go here, for example:
    // Create the next view controller.
    <#DetailViewController#> *detailViewController = [[<#DetailViewController#> alloc] initWithNibName:<#@"Nib name"#> bundle:nil];
    
    // Pass the selected object to the new view controller.
    
    // Push the view controller.
    [self.navigationController pushViewController:detailViewController animated:YES];
}
*/

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
