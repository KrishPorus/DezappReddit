//
//  myTableViewCell.h
//  redditApp
//
//  Created by vpalakur on 12/19/14.
//  Copyright (c) 2014 vpalakur. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface myTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *titleName;
@property (weak, nonatomic) IBOutlet UILabel *authorName;
@property (weak, nonatomic) IBOutlet UILabel *scoreName;
@property (weak, nonatomic) IBOutlet UILabel *dateName;
@property (weak, nonatomic) IBOutlet UIImageView *thumbnail;

@property (weak, nonatomic) NSString *title;
@property (weak, nonatomic) NSString *author;
@property (weak, nonatomic) NSString *score;
@property (weak, nonatomic) NSString *date;


@end
