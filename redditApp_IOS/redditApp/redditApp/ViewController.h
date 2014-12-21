//
//  ViewController.h
//  redditApp
//
//  Created by vpalakur on 12/19/14.
//  Copyright (c) 2014 vpalakur. All rights reserved.
//

#import <UIKit/UIKit.h>
#include "myTableViewController.h"

@interface ViewController : UIViewController<UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *searchText;
@property (strong, nonatomic) NSString *result;

@end

