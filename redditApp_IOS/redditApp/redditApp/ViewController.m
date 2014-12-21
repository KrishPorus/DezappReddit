//
//  ViewController.m
//  redditApp
//
//  Created by vpalakur on 12/19/14.
//  Copyright (c) 2014 vpalakur. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.searchText.delegate = self;
    // Do any additional setup after loading the view, typically from a nib.
}

- (void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    myTableViewController *svc = [segue destinationViewController];
    svc.jsonData = _result;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)searchButton:(id)sender {
    //NSString *str = self.searchText.text;
    //NSLog(@"%@",str);
    NSString *ReqURLStr = [@"http://www.reddit.com/r/pics/search.json?q=" stringByAppendingString: self.searchText.text];
    
    //NSLog(@"%@", ReqURLStr);
    
    //NSLog(@"---------");
    
    NSURLRequest *Request = [NSURLRequest requestWithURL:[NSURL URLWithString:ReqURLStr]];
    
    NSURLResponse *resp = nil;
    
    NSError *error = nil;
    
    NSData *response = [NSURLConnection sendSynchronousRequest:Request returningResponse: &resp error: &error];
    
    NSString *responseString = [[NSString alloc] initWithData:response encoding:NSUTF8StringEncoding];
    
    _result = responseString;
    //NSLog(@"%@",responseString);
}

/*
- (IBAction)searchButtonPressed:(id)sender {
    NSString *str = self.searchText.text;
    NSLog(@"%@",str);
    NSString *ReqURLStr = [@"http://www.reddit.com/r/pics/search.json?q=" stringByAppendingString: self.searchText.text];
    
    NSLog(@"%@", ReqURLStr);
    
    NSLog(@"---------");
    
    NSURLRequest *Request = [NSURLRequest requestWithURL:[NSURL URLWithString:ReqURLStr]];
    
    NSURLResponse *resp = nil;
    
    NSError *error = nil;
    
    NSData *response = [NSURLConnection sendSynchronousRequest:Request returningResponse: &resp error: &error];
    
    NSString *responseString = [[NSString alloc] initWithData:response encoding:NSUTF8StringEncoding];
    
    _result = responseString;
     NSLog(@"%@",responseString);
    
}
 */

- (bool) textFieldShouldReturn:(UITextField *)textField{
    [textField resignFirstResponder];
    return NO;
}

@end
